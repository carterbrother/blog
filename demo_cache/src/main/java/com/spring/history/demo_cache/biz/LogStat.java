package com.spring.history.demo_cache.biz;


import com.spring.history.demo_cache.core.util.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogStat {

	// 1095** 系统账单
	// 错误、慢日志
	// 账单ID|时间|项目名|机器|账单类型(http|db|redis|shell|error)|阀值|类名|方法名|HTTPURL|shell脚本|参数|耗时
	public final static int SLOW = 109501;

	// 性能相关
	// 账单ID|时间|项目名|机器|活跃线程|最大活跃线程|系统CPU负载|系统线程负载|ygc次数|ygc总耗时|ogc总次数|ogc总耗时
	public final static int SYSTEM = 109502;

	// DB|Redis统计相关
	// 账单ID|时间|项目名|机器|类名|方法名|总调用次数|最大耗时|总耗时|0-50|50-100|100-500|>500
	public final static int STAT = 109503;
	
	// 接口信息
	// 109504|时间|项目名|类名|方法名|消费者IP|提供者IP|类型[provider|consumer]|成功次数|错误次数|平均耗时|并发数|1分钟总耗时|1分钟最大并发数
	public final static int INTER = 109504;
	
	static Logger logger = LoggerFactory.getLogger("LogSysStat");
	
	public static void account(int account,Object...args) {
		Object projectName = ServerResource.getProp("server.name");
		StringBuffer log = new StringBuffer();
		log.append(account).append("|");
		log.append(System.currentTimeMillis()/1000).append("|");
		log.append(projectName == null ? "" : projectName).append("|");
		log.append(ComputerInfo.gethostName()).append("|");
		
		for(Object arg : args) {
			log.append(arg == null ? "" : arg).append("|");
		}
		logger.info(log.substring(0, log.length()-1));
	}
	
	public static void account(int account,String content,Logger logFile) {
		Object projectName = ServerResource.getProp("server.name");
		StringBuffer log1 = new StringBuffer();
		log1.append(account).append("|");
		log1.append(System.currentTimeMillis()/1000).append("|");
		log1.append(projectName == null ? "" : projectName).append("|");
		log1.append(ComputerInfo.gethostName()).append("|");
		log1.append(content);
		logFile.info(log1.toString());
	}
	
}
