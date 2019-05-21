package com.springx.bootdubbo.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class SystemUtil {

    private static final String ENV_ONLINE = "online";

    public static final String ENV_DUBBO_VERSION = "lifesense_dubbo_version";

    public static final String PROJECT_CONFIG_FILE = "project_config.properties";
    public static final String SOA_ENABLE = "soa_enable";

    public final static String getEnv() {
        return System.getProperty("disconf.env");
    }

    public static boolean isOnlineEnv() {
        return ENV_ONLINE.equalsIgnoreCase(getEnv());
    }

    /**
     * 是否测试环境判断
     *
     * @return
     */
    public static boolean isTestEnv() {
        return !isOnlineEnv();
    }

    /**
     * 获取系统主机名
     *
     * @return
     */
    public static String getHostname() {
        return System.getProperty("ops.hostname");
    }

    /**
     * 判断当前应用是否应该启动后台任务(kafka/定时任务)
     *
     * @return
     */
    public static boolean shouldStartupBackend() {
        return !isRest() || !isNeedSoa();
    }

    /**
     * 是否rest节点
     *
     * @return
     */
    public static boolean isRest() {
        return StringUtils.contains(getHostname(), "-services-rest-");
    }

    public static String getDubboVersion() {
        return System.getenv(ENV_DUBBO_VERSION);
    }


    /**
     * 既不是后台服务，也不是前端服务
     *
     * @return
     */
    public static boolean notRestAndBackend() {
        return !StringUtils.contains(getHostname(), "-services-rest-") && !StringUtils.contains(getHostname(), "-services-soa-");
    }

    public final static String getLogEnv() {
        StringBuilder sb = new StringBuilder(Optional.ofNullable(getEnv()).orElse(""));
        if (StringUtils.isNotEmpty(GrayUtil.getGrayEnvVar())) {
            sb.append(":").append(GrayUtil.getGrayEnvVar());
        }
        return sb.toString();
    }


    public final static boolean isNeedSoa() {
        InputStream resourceAsStream = SystemUtil.class.getClassLoader().getResourceAsStream(PROJECT_CONFIG_FILE);
        if (resourceAsStream != null) {
            Properties properties = new Properties();
            try {
                properties.load(resourceAsStream);
                return !StringUtils.equals(properties.getProperty(SOA_ENABLE), "false");
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            } finally {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
