package com.springx.bootdubbo.common.util;

import com.springx.bootdubbo.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 日期处理工具类
 *
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @description <br>
 * @date 2015年10月27日
 * Copyright (c) 2015, lifesense.com
 */
public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DATESTAMP_PATTERN = "yyyyMMdd";

    /**
     * 获取年龄
     *
     * @param birthday 生日，日期
     * @return
     */
    public static int getAge(Date birthday) {
        long age = ChronoUnit.YEARS.between(DateUtil.uDateToLocalDateTime(Objects.requireNonNull(birthday, "生日不能为空")).toLocalDate(), LocalDate.now());
        if (age < 0L) {
            age = 0L;
        }
        return (int) age;
    }

    /**
     * 获取年龄
     *
     * @param birthday 生日，时间戳
     * @return
     */
    public static int getAge(Long birthday) {
        long age = ChronoUnit.YEARS.between(DateUtil.uTimeLong2LocalDateTime(Objects.requireNonNull(birthday, "生日时间戳不能为空")).toLocalDate(), LocalDate.now());
        if (age < 0L) {
            age = 0L;
        }
        return (int) age;
    }

    /**
     * 解析日期<br>
     * 支持格式：<br>
     * generate by: vakin jiang at 2012-3-1
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        SimpleDateFormat format = null;
        if (StringUtils.isBlank(dateStr))
            return null;
        String _dateStr = dateStr.trim();
        try {
            if (_dateStr.matches("\\d{1,2}[A-Z]{3}")) {
                _dateStr = _dateStr
                        + (Calendar.getInstance().get(Calendar.YEAR) - 2000);
            }
            // 01OCT12
            if (_dateStr.matches("\\d{1,2}[A-Z]{3}\\d{2}")) {
                format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
            } else if (_dateStr.matches("\\d{1,2}[A-Z]{3}\\d{4}.*")) {
                _dateStr = _dateStr.replaceAll("[^0-9A-Z]", "")
                        .concat("000000").substring(0, 15);
                format = new SimpleDateFormat("ddMMMyyyyHHmmss", Locale.ENGLISH);
            } else {
                StringBuffer sb = new StringBuffer(_dateStr);
                String[] tempArr = _dateStr.split("\\s+");
                tempArr = tempArr[0].split("-|\\/");
                if (tempArr.length == 3) {
                    if (tempArr[1].length() == 1) {
                        sb.insert(5, "0");
                    }
                    if (tempArr[2].length() == 1) {
                        sb.insert(8, "0");
                    }
                }
                _dateStr = sb.append("000000").toString().replaceAll("[^0-9]",
                        "").substring(0, 14);
                if (_dateStr.matches("\\d{14}")) {
                    format = new SimpleDateFormat("yyyyMMddHHmmss");
                }
            }
            Date date = format.parse(_dateStr);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("无法解析日期字符[" + dateStr + "]");
        }
    }

    /**
     * 解析日期字符串转化成日期格式<br>
     * generate by: vakin jiang at 2012-3-1
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat format = null;
            if (StringUtils.isBlank(dateStr)) {
                return null;
            }
            if (StringUtils.isNotBlank(pattern)) {
                format = new SimpleDateFormat(pattern);
                return format.parse(dateStr);
            }
            return parseDate(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("无法解析日期字符[" + dateStr + "]");
        }
    }

    /**
     * 获取一天开始时间<br>
     * generate by: vakin jiang at 2011-12-23
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        String format = DateFormatUtils.format(date, DATE_PATTERN);
        return parseDate(format.concat(" 00:00:00"));
    }

    /**
     * 获取一天结束时间<br>
     * generate by: vakin jiang at 2011-12-23
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        String format = DateFormatUtils.format(date, DATE_PATTERN);
        return parseDate(format.concat(" 23:59:59"));
    }

    /**
     * 时间戳格式转换为日期（年月日）格式<br>
     * generate by: vakin jiang at 2011-12-23
     *
     * @param date
     * @return
     */
    public static Date timestamp2Date(Date date) {
        return formatDate(date, DATE_PATTERN);
    }

    /**
     * 格式化日期格式为：ddMMMyy<br>
     * generate by: vakin jiang
     * at 2012-10-17
     *
     * @return
     */
    public static String format2ddMMMyy(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
        return format.format(date).toUpperCase();
    }

    /**
     * 格式化日期格式为：ddMMMyy<br>
     * generate by: vakin jiang
     * at 2012-10-17
     *
     * @param dateStr
     * @return
     */
    public static String format2ddMMMyy(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
        return format.format(DateUtil.parseDate(dateStr)).toUpperCase();
    }

    /**
     * 格式化日期字符串<br>
     * generate by: vakin jiang at 2012-3-7
     *
     * @param dateStr
     * @param patterns
     * @return
     */
    public static String formatDateStr(String dateStr, String... patterns) {
        String pattern = TIMESTAMP_PATTERN;
        if (patterns != null && patterns.length > 0
                && StringUtils.isNotBlank(patterns[0])) {
            pattern = patterns[0];
        }
        return DateFormatUtils.format(parseDate(dateStr), pattern);
    }

    /**
     * 格式化日期为日期字符串<br>
     * generate by: vakin jiang at 2012-3-7
     *
     * @param patterns
     * @return
     */
    public static String format(Date date, String... patterns) {
        if (date == null) {
            return "";
        }
        String pattern = TIMESTAMP_PATTERN;
        if (patterns != null && patterns.length > 0
                && StringUtils.isNotBlank(patterns[0])) {
            pattern = patterns[0];
        }
        return DateFormatUtils.format(date, pattern);
    }

    public static String format2DateStr(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 格式化日期为指定格式<br>
     * generate by: vakin jiang at 2012-3-7
     *
     * @param orig
     * @param patterns
     * @return
     */
    public static Date formatDate(Date orig, String... patterns) {
        String pattern = TIMESTAMP_PATTERN;
        if (patterns != null && patterns.length > 0
                && StringUtils.isNotBlank(patterns[0])) {
            pattern = patterns[0];
        }
        return parseDate(DateFormatUtils.format(orig, pattern));
    }

    /**
     * 比较两个时间相差多少秒
     */
    public static long getDiffSeconds(Date d1, Date d2) {
        return Math.abs((d2.getTime() - d1.getTime()) / 1000);
    }

    /**
     * 比较两个时间相差多少分钟
     */
    public static long getDiffMinutes(Date d1, Date d2) {
        long diffSeconds = getDiffSeconds(d1, d2);
        return diffSeconds / 60;
    }

    /**
     * 比较两个时间相差多少天，按精准的24小时计算
     */
    public static long getDiffDay(Date d1, Date d2) {
        long between = Math.abs((d2.getTime() - d1.getTime()) / 1000);
        long day = between / 60 / 60 / 24;
        return (long) Math.floor(day);
    }

    /**
     * 比较两个时间相差多少天，按精准的24小时计算
     */
    public static long getDiffDay3(Date d1, Date d2) {
        long between = (d2.getTime() - d1.getTime()) / 1000;
        long day = between / 60 / 60 / 24;
        return (long) Math.floor(day);
    }

    /**
     * 比较两个时间相差多少天，按自然天算（注：跨年会有问题）
     *
     * @param fDate
     * @param oDate
     * @return
     */
    @Deprecated
    public static int getDiffDay2(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    /**
     * 返回传入时间月份的第一天
     */
    public static Date firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 返回指定日期的上个月的第一天
     *
     * @return
     */
    public static Date addFirstDayOfMonth(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + amount, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 返回传入时间月份的最后一天
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @return
     */
    public static Date addLastDayOfMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + amount, 1);
        calendar.roll(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     * 返回传入时间季度的第一天
     */
    public static Date firstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return firstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date firstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return getBeginDate(calendar.getTime());


    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date firstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return firstDayOfMonth(year, month);
    }

    /**
     * 返回指定季度的上一季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date firstDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            year--;
            month = 10 - 1;
        } else if (quarter == 2) {
            month = 1 - 1;
        } else if (quarter == 3) {
            month = 4 - 1;
        } else if (quarter == 4) {
            month = 7 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return firstDayOfMonth(year, month);
    }

    /**
     * 返回传入时间季度的最后一天
     */
    public static Date lastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return lastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date lastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return lastDayOfMonth(year, month);
    }

    /**
     * 返回指定季度的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date lastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month;
        if (quarter == 1) {
            year--;
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return lastDayOfMonth(year, month);
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date lastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return getEndDate(calendar.getTime());
    }

    /**
     * 返回传入时间年份的第一天
     */
    public static Date firstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_YEAR, value);
        return cal.getTime();
    }

    /**
     * 返回传入时间加减amount后，年的第一天
     */
    public static Date addFirstDayOfYear(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, getYear(date) + amount);
        int value = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_YEAR, value);
        return cal.getTime();
    }

    /**
     * 返回传入时间年份的最后一天
     */
    public static Date lastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_YEAR, value);
        return cal.getTime();
    }

    /**
     * 返回传入时间加减amount后，年的最后一天
     */
    public static Date addLastDayOfYear(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, getYear(date) + amount);
        int value = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_YEAR, value - 1);
        return cal.getTime();
    }

    /**
     * 返回传入时间所在礼拜的第一天（周日）
     */
    public static Date firstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_WEEK);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, value);
        return cal.getTime();
    }

    /**
     * 获取两个时间相差月份
     */
    public static int getDiffMonth(Date start, Date end) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        return (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12
                + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }

    /**
     * 计算并格式化消耗时间<br>
     * generate by: vakin jiang at 2012-2-16
     *
     * @param startPoint
     * @return
     */
    public static String formatTimeConsumingInfo(long startPoint) {
        StringBuffer buff = new StringBuffer();
        long totalMilTimes = System.currentTimeMillis() - startPoint;
        int hour = (int) Math.floor(totalMilTimes / (60 * 60 * 1000));
        int mi = (int) Math.floor(totalMilTimes / (60 * 1000));
        int se = (int) Math.floor((totalMilTimes - 60000 * mi) / 1000);
        if (hour > 0) {
            buff.append(hour).append("小时");
        }
        if (mi > 0) {
            buff.append(mi).append("分");
        }
        if (hour == 0) {
            buff.append(se).append("秒");
        }
        return buff.toString();
    }

    /**
     * 判断是否为闰年<br>
     * generate by: zengqw at 2012-9-26
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 添加指定天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DATE, amount);
    }

    /**
     * 添加指定小时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 添加指定小时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinute(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 添加指定月数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 添加指定年数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 计算某一天在一年中的那一周
     */
    public static int getWeekNoForYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 星期一作为每周的第一天
        cal.setTime(date);
        int weekno = cal.get(Calendar.WEEK_OF_YEAR);
        return weekno;
    }

    public static int getYear(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    public static int getMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static int getDay(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获得星期几，1是星期日，7是星期六
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * 24小时制
     *
     * @param date
     * @return
     */
    public static int getHourOfDay(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int day = cal.get(Calendar.HOUR_OF_DAY);
        return day;
    }

    /**
     * 获取分钟
     *
     * @param date
     * @return
     */
    public static int getMinuteOfDay(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int day = cal.get(Calendar.MINUTE);
        return day;
    }

    /**
     * 12小时制
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar cal = new GregorianCalendar();
        if (date != null) {
            cal.setTime(date);
        }
        int day = cal.get(Calendar.HOUR);
        return day;
    }

    public static Date getFirstDateOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        return getBeginDate(cal.getTime());
    }

    public static Date getBeginDate(Date date1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    public static Date getEndDate(Date date1) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }

    /**
     * 计算某个时间之前的多少天
     */
    public static Date getBeforeDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
        return calendar.getTime();
    }

    /**
     * 计算年龄
     *
     * @param birthDay
     * @param currentDate
     * @return
     */
    public static int getAge(Date birthDay, Date currentDate) {
        if (birthDay == null) {
            birthDay = new Date();
        }

        if (currentDate.after(birthDay)) {
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthDay);
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.set(Calendar.MONTH, birth.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, birth.get(Calendar.DAY_OF_MONTH));
            int birthYear = birth.get(Calendar.YEAR);
            int currentYear = cal.get(Calendar.YEAR);
            int age = currentYear - birthYear;
            if (currentDate.before(cal.getTime())) {
                age = age - 1;
            }
            return (age);
        } else {
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthDay);
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.set(Calendar.MONTH, birth.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, birth.get(Calendar.DAY_OF_MONTH));
            int birthYear = birth.get(Calendar.YEAR);
            int currentYear = cal.get(Calendar.YEAR);
            int age = currentYear - birthYear;
            if (currentDate.after(cal.getTime())) {
                age = age + 1;
            }
            return (age);
        }
    }

    /**
     * 取出某个时间所在周的第一天的日期数据
     *
     * @param date 某个时间
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (cal.getTime());
    }

    /**
     * 某周的最后一天时间
     *
     * @param date
     * @return
     */
    public static Date getEndDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return (cal.getTime());
    }

    /**
     * 自然日，以每周一为一周开始
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addFirstDateOfWeek(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) + amount);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getBeginDate(cal.getTime());
    }

    /**
     * 自然日，以每周一为一周开始
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addLastDateOfWeek(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) + amount);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getEndDate(cal.getTime());
    }


    /**
     * 取出某个时间所在周的第一天的日期数据
     *
     * @param date           某个时间
     * @param firstDayOfWeek 一周从哪天开始
     * @return
     */
    @Deprecated
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        return getFirstDayOfWeek(date);
    }

    /**
     * 某周的最后一天时间
     *
     * @param date
     * @param firstDayOfWeek
     * @return
     */
    @Deprecated
    public static Date getEndDayOfWeek(Date date, int firstDayOfWeek) {
        return getEndDayOfWeek(date);
    }

    /**
     * 计算某个时间之后的多少天
     */
    public static Date getAfterDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
        return calendar.getTime();
    }

    /**
     * 根据模板生成月、日、时、分字符串
     *
     * @param date
     * @param template 模板，如{0}月{1}日，{2}点{3}分
     * @return
     */
    public static String formatTemplateDate(Date date, String template) {
        String val = "";
        try {
            if (null == date) {
                date = new Date();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            val = MessageFormat.format(template, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), get_hh(calendar), get_mm(calendar));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    /**
     * formatTemplateDate
     *
     * @param calendar
     * @return
     */
    public static String get_hh(Calendar calendar) {
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);//小时
        String hs = hour.toString();
        if (hour < 10) {
            hs = "0" + hs;
        }
        return hs;
    }

    /************************ 模板 时间 end ************************/

    /**
     * formatTemplateDate
     *
     * @param calendar
     * @return
     */
    public static String get_mm(Calendar calendar) {
        int minute = calendar.get(Calendar.MINUTE);//分
        if (minute < 10) {
            return "0" + minute;
        }
        return String.valueOf(minute);
    }

    /**
     * 根据年龄查询参数获取最小出生日期条件
     *
     * @param age
     * @return age
     */
    public static Long getBirthdayByAgeParam(Integer age) {
        if (age == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -age);
        return calendar.getTimeInMillis();
    }

    /**
     * 根据年龄查询参数获取最大出生日期条件
     *
     * @return age
     */
    public static Long getMaxBirthdayByAgeParam(Integer age) {
        if (age == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, (-age - 1));
        c.add(Calendar.DAY_OF_YEAR, 1);
        return c.getTimeInMillis();
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int calcDayOffset(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        //不一年
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                // 闰年
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else {  // 不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //同一年
            return day2 - day1;
        }
    }

    /**
     * 计算两个时间相关多少年多少月
     *
     * @param date1:大的时间
     * @param date2：小的时间
     * @return 返回字符串 **岁**月
     */
    public static String calcageStr(Date date1, Date date2) {
        // 数据有问题
        if (date1.getTime() < date2.getTime()) {
            return null;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 == year2) {
            if (month1 >= month2) {
                int monthV3 = month1 - month2;
                return monthV3 + "月";
            } else {
                return null;
            }
        } else {
            int monthV1 = (year1 - year2) * 12;
            int monthV2 = monthV1 + month1 - month2;
            int yearV1 = monthV2 / 12;
            int monthV3 = monthV2 % 12;
            if (yearV1 == 0) {
                return monthV3 + "月";
            } else {
                if (monthV3 == 0) {
                    return yearV1 + "岁";
                } else {
                    return yearV1 + "岁" + monthV3 + "月";
                }

            }
        }
    }

    /**
     * 计算两天之间的间隔周数
     *
     * @return Integer
     */
    public static Integer countTwoDayWeek(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        int dayOffset = calcDayOffset(startDate, endDate);

        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = (dayOffset % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            a = (dayOfWeek + dayOffset % 7 < 1) ? -1 : 0;
        }
        weekOffset = weekOffset + a;
        return weekOffset;
    }

    /**
     * 当前时间戳转换成LocalDateTime
     *
     * @param timeLong 当前时间戳，毫秒
     * @return LocalDateTime
     */
    public static LocalDateTime uTimeLong2LocalDateTime(long timeLong) {
        return uDateToLocalDateTime(new Date(timeLong));
    }

    /**
     * localDatetime转换为毫秒
     *
     * @param localDateTime
     * @return 转换的毫秒
     */
    public static long uLocalDateTime2TimeLong(LocalDateTime localDateTime) {
        Long milliSecond = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return milliSecond;
    }

    public static LocalDateTime uDateToLocalDateTime(Date date) {

        if (Objects.isNull(date)) {
            throw new BaseException(205, "日期参数为空");
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    public static Date uLocalDateToDate(LocalDate localDateTime) {
        if (Objects.isNull(localDateTime)) {
            throw new BaseException(206, "日期参数为空");
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atStartOfDay(zone).toInstant();
        java.util.Date date = Date.from(instant);
        return date;
    }

    public static Date uLocalDateTimeToDate(LocalDateTime localDateTime) {

        if (Objects.isNull(localDateTime)) {
            throw new BaseException(206, "日期参数为空");
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
        return date;
    }

    /**
     * 获取相对当前时间，上周一的起始日期
     *
     * @return
     */
    public static Date getLastWeedStartDate() {
        LocalDate today = LocalDate.now();
        int weekValue = today.getDayOfWeek().getValue();
        final Date begin = uLocalDateTimeToDate(LocalDateTime.of(today, LocalTime.of(0, 0, 0, 0)).minusWeeks(1).minusDays(weekValue).plusDays(1));
        return begin;

    }

    /**
     * 获取相对当前时间，上周日的结束日期
     *
     * @return
     */
    public static Date getLastWeedEndDate() {
        LocalDate today = LocalDate.now().minusDays(1);
        int weekValue = today.getDayOfWeek().getValue();
        final Date end = uLocalDateTimeToDate(LocalDateTime.of(today, LocalTime.of(23, 59, 59, 99999)).minusDays(weekValue));
        return end;
    }


}
