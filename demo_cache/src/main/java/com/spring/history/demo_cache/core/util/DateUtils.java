package com.spring.history.demo_cache.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author relax
 * @version Created on 2013-9-16 下午4:18:26
 * 
 * @Describe
 */
public class DateUtils {
	
	/** 时间格式 yyyy-MM-dd */
	public static final String FORMAT_ONE = "yyyy-MM-dd";
	/** 时间格式 yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm:ss";
	/** 时间格式 yyyy-MM */
	public static final String FORMAT_THREE = "yyyy-MM";
	/** 时间格式 yyyyMMddHHmmss */
	public static final String FORMAT_FOUR = "yyyyMMddHHmmss";
	/** 时间格式 yyyyMMddHHmmssSSS */
	public static final String FORMAT_FOURS = "yyyyMMddHHmmssSSS";
	/** 时间格式 yyyy-MM-ddTHH:mm:ss */
	public static final String FORMAT_FIVE = "yyyy-MM-dd'T'HH:mm:ss";
	/** 时间格式 yyyyMMddHHmmssSSS */
	public static final String FORMAT_YMD = "yyyyMMdd";
	/** 时间格式 MMddHHmmss */
	public static final String FORMAT_MDHMS = "MMddHHmmss";
	/** 时间格式 ddHHmm */
	public static final String FORMAT_DHI = "ddHHmm";
	/** 时间格式 MMddHHmm */
	public static final String FORMAT_MDHM = "MMddHHmm";
	/** 时间格式 MMdd */
	public static final String FORMAT_MD = "MMdd";
	
	public static final long DAY = 24 * 60 * 60 * 1000; 
	public static final long HOUR = 60 * 60 * 1000; 
	public static final long MINUTE = 60 * 1000; 
	
	
	public static String LOG_SUFFIX = null;
	/**
	 * 获取日志的前后缀
	 * 
	 * @return
	 */
	public static String getSuffix(){
		if(LOG_SUFFIX == null){
			LOG_SUFFIX = DateUtils.getYearAndNowMonth();
		}
		return LOG_SUFFIX;
	}
	
	/**
	 * 将String类型的时间转化成Date类型的时间
	 * 
	 * @param time   时间
	 * @param format 格式
	 * @return
	 */
	public static Date stringToDate(String time, String format) {
		if (time == null || "".equals(time))
			return new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			Date date = dateFormat.parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Date类型的时间转化成String类型的时间
	 * 
	 * @param time
	 *            时间
	 * @param format
	 *            格式
	 * @return
	 */
	public static String dateToString(Date time, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String date = dateFormat.format(time);
		return date;
	}
	/**
	 * Date转时间戳
	 * @param time
	 * @param format
	 * @return
	 */
	public static int dateToTs(Date time, String format) {
		String dateStr = dateToString(time, format);
		int ts = (int)strtotime(dateStr, format);
		return ts;
	}
	/**
	 * 获取当前时间 格式 yyyy-MM-dd HH:mm:ss 
	 * @return
	 */
	public static String getNOW(){
		return DateUtils.dateToString(new Date(), FORMAT_TWO);
	}
	/**
	 * 获取当前的小时
	 * @return
	 */
	public static String getCurHour(){
		return DateUtils.dateToString(new Date(), "HH");
	}
	/**
	 * 获取当前月
	 * 例:2019-12-15时return 12
	 */
	public static String getMM(){
		return DateUtils.dateToString(new Date(), "MM");
	}
	/**
	 * 获取当前的天
	 * 例:2019-12-15时return 15
	 */
	public static String getDD(){
		return DateUtils.dateToString(new Date(), "dd");
	}
	
	/**
	 * 自定义获取当前时间
	 * @param format
	 * @return
	 */
	public static String getNOW(String format){
		return DateUtils.dateToString(new Date(), format);
	}


	/**
	 * 自定义获取前几天
	 * @param format
	 * @return
	 */
	public static String getBeforeDay(String format,int before){

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -before);
		date = calendar.getTime();
		return DateUtils.dateToString(date, format);
	}

	/**
	 * 将时间戳转化成String类型的时间
	 * 
	 * @param time
	 *            时间戳
	 * @param format
	 *            格式
	 * @return
	 */
	public static String dateToString(long time, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String date = dateFormat.format(time);
		return date;
	}
	/**
	 * 日期转时间戳
	 * @param dateStr 2018-11-01
	 * @param format  yyyy-dd-mm
	 * @return 1541001600
	 */
	public static long strtotime(String dateStr,String format) {
	     SimpleDateFormat sdf = new SimpleDateFormat(format);  
	     try {
	    	return sdf.parse(dateStr).getTime()/1000;  
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 验证指定时间是否是当天
	 * @param time
	 *
	 * @return true: 是同一天
	 */
	public static boolean compareData(long time) {
		return compareData(dateToString(time,FORMAT_ONE));
	}
	/**
	 * 验证当前时间的day是否与当前时间day相等
	 *
	 * @return 同一天/true
	 */
	public static boolean compareData(Date time) {
		if(time == null) return false;
		return dateToString(time, FORMAT_ONE).equals(dateToString(new Date(), FORMAT_ONE));
	}
	
	/**
	 * 验证当day是否相等
	 *
	 * @param time1
	 * @param time2
	 * @return 同一天/true
	 */
	public static boolean compareData(Date time1, Date time2) {
		if(time1 == null || time2 == null) return time1 == time2;
		return dateToString(time1, FORMAT_ONE).equals(dateToString(time2, FORMAT_ONE));
	}

	/**
	 * 验证当前时间的day是否与当前时间day相等
	 * 
	 * @return 同一天/true
	 */
	public static boolean compareData(String time) {
		return time.equals(dateToString(new Date(), FORMAT_ONE));
	}
	
	
	public static boolean canInitLogTable(){
		Calendar calendar = Calendar.getInstance();  
		int maxDay = calendar.getActualMaximum(Calendar.DATE);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return maxDay == day;
	}
	
	public static String getYearAndNowMonth(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 1);
	}
	
	public static String getLogTalbeSuffix(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "" + (calendar.get(Calendar.MONTH) + 2);
	}

    /**
     * 判断给定时间是否在有效期内
     * @param effectiveTime
     * @return
     */
	public static boolean isEffectiveTime(Date effectiveTime){
        return effectiveTime.getTime()-ms()>0?true:false;
	}

	/**
	 * 获取一天的起始时间
	 * @param date
	 *
	 * @return
	 */
	public static Date getStartOfDate(Date date){
		String nowTime = DateUtils.dateToString(date, FORMAT_ONE);
		return DateUtils.stringToDate(nowTime, FORMAT_ONE);
	}
	/**
	 * 获取一天已过时间
	 */
	public static long getPassTime()
	{
		Date d = DateUtils.getStartOfDate(new Date());
		return System.currentTimeMillis() - d.getTime();
	}
	/**
	 * 获取一天剩余时间
	 */
	public static long getlessTime()
	{
		return (DAY - getPassTime())/1000;
	}
	
	/**
	 * 用于固定时间刷新
	 * 判断今天是否到了某个时间点
	 * */
	public static boolean compareNowHour(int hour)
	{
		long time = hour*DateUtils.HOUR;
		
		return DateUtils.getPassTime() >= time;
	}
	
	/**
	 * 用于固定时间刷新
	 * 判断记录的时间是否在固定时间之前
	 * 若是可执行刷新操作
	 * */
	public static boolean compareFixHour(int hour,Date d)
	{
		long recordTime = d.getTime();
		long fixedTime = hour*DateUtils.HOUR + DateUtils.getStartOfDate(new Date()).getTime();
		return recordTime < fixedTime;
	}
	/**
	 * 用于固定时间刷新
	 * 判断记录的时间是否在固定时间之前
	 * 并且是否到了某个时间点
	 * 若是可执行刷新操作
	 * */
	public static boolean canRefresh(int hour,Date d)
	{
		return compareFixHour(hour,d) && compareNowHour(hour);
	}
	
	public static boolean isUseDb(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 1;
	}

	/**
	 * 获取剩余时间
	 * @param startTime 开始时间
	 * @param duration 持续时间
	 *
	 * @return 剩余时间(毫秒)
	 */
	public static long getLeftTime(long startTime, long duration){
		return Math.max(0,  (startTime + duration) - System.currentTimeMillis());
	}
	
	/**
	 * 获取start + day的时间
	 * 
	 * @param start
	 * @param dayTimes
	 * @return
	 */
	public static Date addTime(Date start,long dayTimes){
		long max = dayTimes+ start.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(max);
		return cal.getTime();
	}

	public static int getWeekDay(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 对比两个时间相差天数
	 * 
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return 日-日
	 * 例：27日-27日=0
		  27日-26日=1
	 */
	public static int daysOfTwo(long start, long end) {
	    Calendar aCalendar = Calendar.getInstance();
	    aCalendar.setTimeInMillis(start);
	    int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	    aCalendar.setTimeInMillis(end);
	    int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	    return day2 - day1;
	}
	/**
	 * 对比两个时间相差天数
	 * start到end间隔多少天
	 * @param start 秒级时间戳
	 * @param end   秒级时间戳
	 */
	public static int daysOfTs(int start, int end) {
		return (end-start)/(3600*24);
	}
	/**
	 * 获取当前秒级时间戳
	 * @return
	 */
	public static int time(){
		return (int)(System.currentTimeMillis() / 1000);
	}
	/**
	 * 获取下个月的日期
	 * @param format yyyy-MM-dd HH:mm:ss,yyyy-MM-dd
	 * @return 2018-11-18 20:20:01,2018-11-18
	 */
	public static String getNextMonth(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		calendar.add(Calendar.MONTH, 1);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 获取当月剩余的时间戳
	 */
	public static int getMonthLessTime() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);//当前时间
		calendar.add(Calendar.MONTH, 1);//+1个月=下个月
		calendar.set(Calendar.DAY_OF_MONTH,1);//1号
		calendar.set(Calendar.HOUR_OF_DAY, 0);//0点
		calendar.set(Calendar.MINUTE, 0);//0分
		calendar.set(Calendar.SECOND,0);//0秒
		calendar.set(Calendar.MILLISECOND, 0);//0毫秒
		long lessTime = calendar.getTimeInMillis()-date.getTime();//减当前时间
		return new Long(lessTime/1000).intValue();//毫秒转秒级
	}
	public static String getMonthFirstDay(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//1:本月第一天
		return sdf.format(c.getTime());
	}
	//获取昨天
	public static String getYesterday(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		return sdf.format(calendar.getTime());
	}
	//获取今天
	public static String getToday(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return sdf.format(calendar.getTime());
	}
	//获取明天
	public static String getTomorrow(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		
		return sdf.format(calendar.getTime());
	}
	public static String getYMD() {
		return DateUtils.dateToString(new Date(), FORMAT_ONE);
	}
	public static String getMD() {
		return DateUtils.dateToString(new Date(), FORMAT_MD);
	}
	/**
	 * 获取今天凌晨开始那一时的时间戳
	 * @return
	 */
	public static int getTodayStartTimestamp() {
		String ymd = DateUtils.dateToString(new Date(), DateUtils.FORMAT_ONE);
    	long timstamp = DateUtils.strtotime(ymd, DateUtils.FORMAT_ONE);
    	return (int)timstamp;
	}
	/**
	 * 检测是否在时段内
	 * @param constant 例:22-8
	 * 是否在晚上22点到凌晨8点的时段内
	 */
	public static boolean checkTimePeriod(String constant){
		int [] timeArr = ArrayUtils.parseIntArray(constant,"-");
		
		if(timeArr.length!=2)return false;
		
		Calendar calendar = Calendar.getInstance();
		
		int curHour=calendar.get(Calendar.HOUR_OF_DAY);//当前小时,范围:0-23
		int startHour = timeArr[0];
		int endHour = timeArr[1];
		if(startHour < endHour){//当天时段,如: 20:00-23:00
			if( startHour<curHour && curHour<endHour ){
				return true;
			}
				
		}else if(startHour > endHour){//跨越凌晨,如:20:00-3:00
			if( endHour > curHour ||  startHour <= curHour ){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取n小时的总秒数
	 * @return
	 */
	public static int getSecondsByHour(double n) {
		return (int)(n*60*60);
	}
	/**
	 * 获取n天的总秒数
	 * @return
	 */
	public static int getSecondsByDay(double n) {
		return (int)(n*60*60*24);
	}
	/**
	 * 判断秒级时间戳是否昨天
	 * @param ts 秒级时间戳
	 * @return
	 */
	public static boolean isYesterday(int ts) {
	    long ms = ts * 1000L;//毫秒时间戳
	    String msDateStr = dateToString(ms,DateUtils.FORMAT_ONE);
	    String yesterdayDateStr = getYesterday(DateUtils.FORMAT_ONE);
	    if(msDateStr.equals(yesterdayDateStr)) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	/**
	 * 判断秒级时间戳是否今天
	 * @param ts 秒级时间戳
	 * @return
	 */
	public static boolean isToday(int ts) {
		long ms = ts * 1000L;//毫秒时间戳
		String msDateStr = dateToString(ms,DateUtils.FORMAT_ONE);
		String todayDateStr = getToday(DateUtils.FORMAT_ONE);
		if(msDateStr.equals(todayDateStr)) {
			return true;
		}else {
			return false;
		}
	}
	//获取毫秒级时间戳
	public static long ms() {
		return System.currentTimeMillis();
	}
}
