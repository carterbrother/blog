package com.spring.history.demo_cache.core.state;



import com.spring.history.demo_cache.biz.LogStat;
import com.spring.history.demo_cache.core.queue.MessageManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 方法数据缓存实现
 * 
 */
@Aspect
@Component
public class RedisStat {
	Logger logger = LoggerFactory.getLogger("CustomExceptionFilter");
	
	@Pointcut("execution(* com.zengame.platform.common.tools.redis.RedisOperate.*(..))")
	public void logPointCut() { 
		
	}
	
	@Around("logPointCut()")
	public Object saveCache(ProceedingJoinPoint pj) throws Throwable {
		//类名
		String className = pj.getTarget().getClass().getSimpleName();
		//方法名
		String methodName  = pj.getSignature().getName();
		
		
		long start = System.currentTimeMillis();
		
		Object obj = null;
		obj = pj.proceed();
		
		long end = System.currentTimeMillis();
		
		int use =(int)( end - start);
		
		
		if(MessageManager.getInstance().isDbStat()) {
			RedisStatistic.getInstance().set(new StatData(use,className,methodName));
			if(use > 50 && !"RedisDateCenterCache".equals(className)) {
				Object[]  args = pj.getArgs();
				Object arg = "";
				if(args != null && args.length!=0) {
					arg=args[0];
				}
				LogStat.account(LogStat.SLOW, "redis",50,className,methodName,arg,use);
				//logger.info("redis>50|"+className+"|"+methodName+"|"+arg+"|"+use);
			}
		}
		
		return obj;
	}
	
	
}
