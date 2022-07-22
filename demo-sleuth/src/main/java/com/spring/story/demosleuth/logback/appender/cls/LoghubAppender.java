package com.spring.story.demosleuth.logback.appender.cls;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.tencentcloudapi.cls.producer.AsyncProducerClient;
import com.tencentcloudapi.cls.producer.AsyncProducerConfig;
import com.tencentcloudapi.cls.producer.Result;
import com.tencentcloudapi.cls.producer.common.LogItem;
import com.tencentcloudapi.cls.producer.errors.ProducerException;
import com.tencentcloudapi.cls.producer.util.NetworkUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @param <E>
 * @author farmerx
 */
@Data
@Slf4j
public class LoghubAppender<E> extends UnsynchronizedAppenderBase<E> {

    private String topicId;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String userAgent = "logback";
    private String source = "";
    private String timeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";
    private String timeZone = "UTC";
    private String totalSizeInBytes;
    private String maxBlockMs;
    private String sendThreadCount;
    private String batchSizeThresholdInBytes;
    private String batchCountThreshold;
    private String lingerMs;
    private String retries;
    private String maxReservedAttempts;
    private String baseRetryBackoffMs;
    private String maxRetryBackoffMs;

    private AsyncProducerClient producer;

    private AsyncProducerConfig producerConfig;

    private CustomFieldsJsonProvider customFieldsJsonProvider = new CustomFieldsJsonProvider();

    private DateTimeFormatter formatter;


    protected Encoder<E> encoder;

    private String mdcFields;

    private String customFields;

    //服务名称

    private String serviceName;

    //环境名称

    private String envName;


    private final LoghubAppenderCallback<E> loghubAppenderCallback = new LoghubAppenderCallback<E>() {
        @Override
        public void onCompletion(Result result) {
            if (!result.isSuccessful()) {
                addError("Failed to send log, topic=" + topicId
                        + ", source=" + source
                        + ", errorCode=" + result.getErrorCode()
                        + ", errorMessage=" + result.getErrorMessage());
            }
        }
    };

    @Override
    public void start() {
        try {
            customFieldsJsonProvider.setCustomFields(this.customFields);
            customFieldsJsonProvider.initializeCustomFields();
            if (source == null || source.isEmpty()) {
                source = NetworkUtils.getLocalMachineIP();
            }
            producerConfig = new AsyncProducerConfig(endpoint, accessKeyId, accessKeySecret, source);
            this.setProduceConfig();
            producer = new AsyncProducerClient(producerConfig);
            log.info("===cls log init success!");
            super.start();
        } catch (Exception e) {
            addError("Failed to start LoghubAppender.", e);
        }
    }

    @Override
    public void stop() {
        try {
            doStop();
        } catch (Exception e) {
            addError("Failed to stop LoghubAppender.", e);
        }
    }

    private void doStop() throws InterruptedException, ProducerException {
        if (!isStarted()) {
            return;
        }
        super.stop();
        producer.close();
    }


    @Override
    public void append(E eventObject) {
        try {
            appendEvent(eventObject);
        } catch (Exception e) {
            addError("Failed to append event.", e);
        }
    }

    private void appendEvent(E eventObject) {
        if (!(eventObject instanceof LoggingEvent)) {
            return;
        }
        LoggingEvent event = (LoggingEvent) eventObject;

        LogItem item = new LogItem();
        item.SetTime(event.getTimeStamp());

        item.PushBack("time", LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimeStamp()), ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern(timeFormat)));
        item.PushBack("level", event.getLevel().toString());
        item.PushBack("thread", event.getThreadName());
        item.PushBack("serviceName", getServiceName());
        item.PushBack("envName", getEnvName());
        item.PushBack("logClass", event.getLoggerName());
        item.PushBack("serverIp", getSource());

        StackTraceElement[] caller = event.getCallerData();
        if (caller != null && caller.length > 0) {
            item.PushBack("location", caller[0].toString());
        }

        String message = event.getFormattedMessage();
        if (message.length() > 1024 * 1024) {
            addError("Failed to send log, message content exceed 1M, topicId=" + topicId
                    + ", source=" + source
                    + ", logItem=" + item, new Exception("message content exceed 1M"));
            return;
        }
        item.PushBack("message", message);

        IThrowableProxy iThrowableProxy = event.getThrowableProxy();
        if (iThrowableProxy != null) {
            String throwable = getExceptionInfo(iThrowableProxy);
            throwable += fullDump(event.getThrowableProxy().getStackTraceElementProxyArray());
            item.PushBack("throwable", throwable);
            item.PushBack("exceptionClass", throwable.getClass().getName());
        }

        if (this.encoder != null) {
            String logMessage = new String(this.encoder.encode(eventObject));
            if (logMessage.length() > 1024 * 1024) {
                addError("Failed to send log, message content exceed 1M, topicId=" + topicId
                        + ", source=" + source
                        + ", logItem=" + item, new Exception("message content exceed 1M"));
                return;
            }
            item.PushBack("log", logMessage);
        }

        Optional.ofNullable(mdcFields).ifPresent(
                f -> event.getMDCPropertyMap().entrySet().stream()
                        .filter(v -> Arrays.stream(f.split(",")).anyMatch(i -> i.equals(v.getKey())))
                        .forEach(map -> item.PushBack(map.getKey(), map.getValue()))
        );


        if (customFieldsJsonProvider.getCustomFieldsNode() != null) {
            for (Iterator<Map.Entry<String, JsonNode>> fields = customFieldsJsonProvider.getCustomFieldsNode().fields();
                 fields.hasNext(); ) {
                Map.Entry<String, JsonNode> field = fields.next();
                item.PushBack(field.getKey(), field.getValue().asText());
            }
        }
        try {
            List<LogItem> logItems = new ArrayList<>();
            logItems.add(item);
            producer.putLogs(topicId, logItems, loghubAppenderCallback);
        } catch (Exception e) {
            addError("Failed to send log, topicId=" + topicId
                    + ", source=" + source
                    + ", logItem=" + item
                    + " err message " + e.getMessage());
        }
    }

    private String getExceptionInfo(IThrowableProxy iThrowableProxy) {
        String s = iThrowableProxy.getClassName();
        String message = iThrowableProxy.getMessage();
        return (message != null) ? (s + ": " + message) : s;
    }

    private String fullDump(StackTraceElementProxy[] stackTraceElementProxyArray) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElementProxy step : stackTraceElementProxyArray) {
            builder.append(CoreConstants.LINE_SEPARATOR);
            String string = step.toString();
            builder.append(CoreConstants.TAB).append(string);
            ThrowableProxyUtil.subjoinPackagingData(builder, step);
        }
        return builder.toString();
    }


    private void setProduceConfig() {
        if (totalSizeInBytes != null && !totalSizeInBytes.isEmpty()) {
            producerConfig.setTotalSizeInBytes(Integer.parseInt(totalSizeInBytes));
        }
        if (maxBlockMs != null && !maxBlockMs.isEmpty()) {
            producerConfig.setMaxBlockMs(Long.parseLong(maxBlockMs));
        }
        if (sendThreadCount != null && !sendThreadCount.isEmpty()) {
            producerConfig.setSendThreadCount(Integer.parseInt(sendThreadCount));
        }
        if (batchSizeThresholdInBytes != null && !batchSizeThresholdInBytes.isEmpty()) {
            producerConfig.setBatchSizeThresholdInBytes(Integer.parseInt(batchSizeThresholdInBytes));
        }
        if (batchCountThreshold != null && !batchCountThreshold.isEmpty()) {
            producerConfig.setBatchCountThreshold(Integer.parseInt(batchCountThreshold));
        }
        if (lingerMs != null && !lingerMs.isEmpty()) {
            producerConfig.setLingerMs(Integer.parseInt(lingerMs));
        }
        if (retries != null && !retries.isEmpty()) {
            producerConfig.setRetries(Integer.parseInt(retries));
        }
        if (maxReservedAttempts != null && !maxReservedAttempts.isEmpty()) {
            producerConfig.setMaxReservedAttempts(Integer.parseInt(maxReservedAttempts));
        }
        if (baseRetryBackoffMs != null && !baseRetryBackoffMs.isEmpty()) {
            producerConfig.setBaseRetryBackoffMs(Long.parseLong(baseRetryBackoffMs));
        }
        if (maxRetryBackoffMs != null && !maxRetryBackoffMs.isEmpty()) {
            producerConfig.setMaxRetryBackoffMs(Long.parseLong(maxRetryBackoffMs));
        }
    }
}
