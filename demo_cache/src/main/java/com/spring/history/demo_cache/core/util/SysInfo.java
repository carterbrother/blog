package com.spring.history.demo_cache.core.util;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class SysInfo {
	
 
	static StringBuffer buff = new StringBuffer();
	public static String sysInfo() {
		buff.setLength(0);//清空
		
	    //获取各个线程的各种状态
	    ThreadMXBean threadMBean=(ThreadMXBean)ManagementFactory.getThreadMXBean();  
	    buff.append(threadMBean.getThreadCount()).append("|");
	    buff.append(threadMBean.getPeakThreadCount()).append("|");
	    
	    //获取系统cpu负载、线程负载
	    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	    
	    buff.append(OperateTools.getFloatValue((float) osmxb.getProcessCpuLoad())).append("|");
	    buff.append(OperateTools.getFloatValue((float) osmxb.getSystemCpuLoad())).append("|");
	    
	    //获取GC的次数以及花费时间之类的信息
	    List<GarbageCollectorMXBean> gcMBeanList=ManagementFactory.getGarbageCollectorMXBeans();  
	    for(GarbageCollectorMXBean gc : gcMBeanList){  
	    	long count = gc.getCollectionCount();
            long time = gc.getCollectionTime();
            String name = gc.getName();
            buff.append(String.format("%s|%s|%s|", name, count, time));
	    }
	   
	    return buff.toString();
	}
	
	
}
