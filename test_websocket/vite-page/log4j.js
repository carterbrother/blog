











log4j.rootLogger=INFO,CONSOLE,DAILY_FILE,ERROR_FILE

log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE.Threshold=DEBUG
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
log4j.appender.CONSOLE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %5p %c - %m%n

#统一日志输出
log4j.appender.DAILY_FILE=org.apache.log4j.DailyRollingFileAppender
log4j.appender.DAILY_FILE.Threshold=INFO
log4j.appender.DAILY_FILE.File=logs/info/info.log
log4j.appender.DAILY_FILE.DatePattern='.'yyyy-MM-dd
log4j.appender.DAILY_FILE.Append=true
log4j.appender.DAILY_FILE.Encoding=UTF-8
log4j.appender.DAILY_FILE.layout=org.apache.log4j.PatternLayout
log4j.appender.DAILY_FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %5p %c - %m%n

log4j.logger.V4Url=DEBUG,T
log4j.additivity.V4Url=false
log4j.appender.T=org.apache.log4j.DailyRollingFileAppender
log4j.appender.T.Threshold=INFO
log4j.appender.T.File=logs/trace/trace.log
log4j.appender.T.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.T.Append=true
log4j.appender.T.Encoding=UTF-8
log4j.appender.T.layout=org.apache.log4j.PatternLayout
log4j.appender.T.layout.ConversionPattern=%m%n

#
log4j.appender.ERROR_FILE=org.apache.log4j.DailyRollingFileAppender
log4j.appender.ERROR_FILE.Threshold=error
log4j.appender.ERROR_FILE.File=logs/error.log
log4j.appender.ERROR_FILE.DatePattern='.'yyyy-MM-dd
log4j.appender.ERROR_FILE.Append=true
log4j.appender.ERROR_FILE.Encoding=UTF-8
log4j.appender.ERROR_FILE.layout=org.apache.log4j.PatternLayout
log4j.appender.ERROR_FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %5p %c - %m%n

log4j.logger.LogApiStat=DEBUG,apiStat,syslog
log4j.additivity.LogApiStat=false
log4j.appender.apiStat=org.apache.log4j.DailyRollingFileAppender
log4j.appender.apiStat.Threshold=INFO
log4j.appender.apiStat.File=logs/apiStat/apiStatSpringBoot.log
log4j.appender.apiStat.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.apiStat.Append=true
log4j.appender.apiStat.Encoding=UTF-8
log4j.appender.apiStat.layout=org.apache.log4j.PatternLayout
log4j.appender.apiStat.layout.ConversionPattern=%m%n

#####################################账单相关#####################################
#客户端上报账单
log4j.logger.LogClientReport=DEBUG,ClientReport,syslog
log4j.additivity.LogClientReport=false
log4j.appender.ClientReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.ClientReport.Threshold=DEBUG
log4j.appender.ClientReport.File=logs/report/clientReport.log
log4j.appender.ClientReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.ClientReport.Append=true
log4j.appender.ClientReport.Encoding=UTF-8
log4j.appender.ClientReport.layout=org.apache.log4j.PatternLayout
log4j.appender.ClientReport.layout.ConversionPattern=%m%n
log4j.appender.ClientReport.BufferedIO=true
log4j.appender.ClientReport.BufferSize=16384
#通用上报
log4j.logger.LogCommonReport=DEBUG,CommonReport,syslog
log4j.additivity.LogCommonReport=false
log4j.appender.CommonReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.CommonReport.Threshold=DEBUG
log4j.appender.CommonReport.File=logs/report/commonReport.log
log4j.appender.CommonReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.CommonReport.Append=true
log4j.appender.CommonReport.Encoding=UTF-8
log4j.appender.CommonReport.layout=org.apache.log4j.PatternLayout
log4j.appender.CommonReport.layout.ConversionPattern=%m%n
log4j.appender.ClientReport.BufferedIO=true
log4j.appender.ClientReport.BufferSize=16384
#启动上报
log4j.logger.LogStartReport=DEBUG,StartReport,syslog
log4j.additivity.LogStartReport=false
log4j.appender.StartReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.StartReport.Threshold=DEBUG
log4j.appender.StartReport.File=logs/report/startReport.log
log4j.appender.StartReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.StartReport.Append=true
log4j.appender.StartReport.Encoding=UTF-8
log4j.appender.StartReport.layout=org.apache.log4j.PatternLayout
log4j.appender.StartReport.layout.ConversionPattern=%m%n
#检查更新上报
log4j.logger.LogCheckUpdateReport=DEBUG,CheckUpdateReport,syslog
log4j.additivity.LogCheckUpdateReport=false
log4j.appender.CheckUpdateReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.CheckUpdateReport.Threshold=DEBUG
log4j.appender.CheckUpdateReport.File=logs/report/checkUpdateReport.log
log4j.appender.CheckUpdateReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.CheckUpdateReport.Append=true
log4j.appender.CheckUpdateReport.Encoding=UTF-8
log4j.appender.CheckUpdateReport.layout=org.apache.log4j.PatternLayout
log4j.appender.CheckUpdateReport.layout.ConversionPattern=%m%n
#检查更新上报
log4j.logger.LogInitReport=DEBUG,InitReport,syslog
log4j.additivity.LogInitReport=false
log4j.appender.InitReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.InitReport.Threshold=DEBUG
log4j.appender.InitReport.File=logs/report/initReport.log
log4j.appender.InitReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.InitReport.Append=true
log4j.appender.InitReport.Encoding=UTF-8
log4j.appender.InitReport.layout=org.apache.log4j.PatternLayout
log4j.appender.InitReport.layout.ConversionPattern=%m%n
#手机上报
log4j.logger.LogCollectReport=DEBUG,CollectReport,syslog
log4j.additivity.LogCollectReport=false
log4j.appender.CollectReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.CollectReport.Threshold=DEBUG
log4j.appender.CollectReport.File=logs/report/collectReport.log
log4j.appender.CollectReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.CollectReport.Append=true
log4j.appender.CollectReport.Encoding=UTF-8
log4j.appender.CollectReport.layout=org.apache.log4j.PatternLayout
log4j.appender.CollectReport.layout.ConversionPattern=%m%n
#小程序上报
log4j.logger.LogMiniAppReport=DEBUG,CollectReport,syslog
log4j.additivity.LogMiniAppReport=false
log4j.appender.CollectReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.CollectReport.Threshold=DEBUG
log4j.appender.CollectReport.File=logs/report/miniApp.log
log4j.appender.CollectReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.CollectReport.Append=true
log4j.appender.CollectReport.Encoding=UTF-8
log4j.appender.CollectReport.layout=org.apache.log4j.PatternLayout
log4j.appender.CollectReport.layout.ConversionPattern=%m%n
#老的单机上报
log4j.logger.LogSingleCommonReport=DEBUG,SingleCommonReport,syslog
log4j.additivity.LogSingleCommonReport=false
log4j.appender.SingleCommonReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.SingleCommonReport.Threshold=DEBUG
log4j.appender.SingleCommonReport.File=logs/report/singleCommonReport.log
log4j.appender.SingleCommonReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.SingleCommonReport.Append=true
log4j.appender.SingleCommonReport.Encoding=UTF-8
log4j.appender.SingleCommonReport.layout=org.apache.log4j.PatternLayout
log4j.appender.SingleCommonReport.layout.ConversionPattern=%m%n
#错误日志上报
log4j.logger.LogBuglyReport=DEBUG,BuglyReport,syslog
log4j.additivity.LogBuglyReport=false
log4j.appender.BuglyReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.BuglyReport.Threshold=DEBUG
log4j.appender.BuglyReport.File=logs/report/buglyReport.log
log4j.appender.BuglyReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.BuglyReport.Append=true
log4j.appender.BuglyReport.Encoding=UTF-8
log4j.appender.BuglyReport.layout=org.apache.log4j.PatternLayout
log4j.appender.BuglyReport.layout.ConversionPattern=%m%n

log4j.logger.LogSingleReport=DEBUG,SingleReport,syslog
log4j.additivity.LogSingleReport=false
log4j.appender.SingleReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.SingleReport.Threshold=DEBUG
log4j.appender.SingleReport.File=logs/report/singleReport.log
log4j.appender.SingleReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.SingleReport.Append=true
log4j.appender.SingleReport.Encoding=UTF-8
log4j.appender.SingleReport.layout=org.apache.log4j.PatternLayout
log4j.appender.SingleReport.layout.ConversionPattern=%m%n

log4j.logger.LogHttpReport=DEBUG,HttpReport,syslog
log4j.additivity.LogHttpReport=false
log4j.appender.HttpReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.HttpReport.Threshold=DEBUG
log4j.appender.HttpReport.File=logs/report/httpReport.log
log4j.appender.HttpReport.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.HttpReport.Append=true
log4j.appender.HttpReport.Encoding=UTF-8
log4j.appender.HttpReport.layout=org.apache.log4j.PatternLayout
log4j.appender.HttpReport.layout.ConversionPattern=%m%n

log4j.logger.LogCliectPayReport=DEBUG,CliectPayReport,syslog
log4j.additivity.LogCliectPayReport=false
log4j.appender.CliectPayReport=org.apache.log4j.DailyRollingFileAppender
log4j.appender.CliectPayReport.Threshold=DEBUG
log4j.appender.CliectPayReport.File=logs/report/cliectPayReport.log
log4j.appender.CliectPayReport.DatePattern='.'yyyy-MM-dd
log4j.appender.CliectPayReport.Append=true
log4j.appender.CliectPayReport.Encoding=UTF-8
log4j.appender.CliectPayReport.layout=org.apache.log4j.PatternLayout
log4j.appender.CliectPayReport.layout.ConversionPattern=%m%n

log4j.logger.LogPayReportRequest=DEBUG,PayReportRequest,syslog
log4j.additivity.LogPayReportRequest=false
log4j.appender.PayReportRequest=org.apache.log4j.DailyRollingFileAppender
log4j.appender.PayReportRequest.Threshold=DEBUG
log4j.appender.PayReportRequest.File=logs/request/payReportRequest.log
log4j.appender.PayReportRequest.DatePattern='.'yyyy-MM-dd-HH
log4j.appender.PayReportRequest.Append=true
log4j.appender.PayReportRequest.Encoding=UTF-8
log4j.appender.PayReportRequest.layout=org.apache.log4j.PatternLayout
log4j.appender.PayReportRequest.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %5p %c - %m%n


#log4j.appender.syslog=org.apache.log4j.net.SyslogAppender
log4j.appender.syslog=com.zengame.platform.common.tools.utils.ZenGameSyslogAppender
log4j.appender.syslog.SyslogHost=localhost
log4j.appender.syslog.Facility=LOCAL5
log4j.appender.syslog.Threshold=INFO
log4j.appender.syslog.header=true
log4j.appender.syslog.layout=org.apache.log4j.PatternLayout
#log4j.appender.syslog.layout.ConversionPattern=%t: %m
#输出格式
log4j.appender.syslog.layout.ConversionPattern=bills: %m

#支付统一账单输出
log4j.logger.LogPayCommon=DEBUG,PayCommon,syslog
log4j.additivity.LogPayCommon=false
log4j.appender.PayCommon=org.apache.log4j.DailyRollingFileAppender
log4j.appender.PayCommon.Threshold=DEBUG
log4j.appender.PayCommon.File=logs/account/paycommon.log
log4j.appender.PayCommon.DatePattern='.'yyyy-MM-dd
log4j.appender.PayCommon.Append=true
log4j.appender.PayCommon.Encoding=UTF-8
log4j.appender.PayCommon.layout=org.apache.log4j.PatternLayout
log4j.appender.PayCommon.layout.ConversionPattern=%m%n
