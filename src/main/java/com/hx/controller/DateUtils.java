package com.hx.controller;

import org.apache.commons.lang3.StringUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String CHINA_YYYY_MM_DD_HH_MM = "yyyy年MM月dd日 HH时mm分";
    private static StringBuffer buffer = new StringBuffer();
    private static String ZERO = "0";
    private static DateUtils date;
    public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat format1 = new SimpleDateFormat(
            "yyyyMMdd HH:mm:ss");

    public static Date parse(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        dateStr = dateStr.trim();
        Date date = null;
        if (dateStr.length() == YYYY.length()) {
            date = parse(dateStr, YYYY);
        } else if (dateStr.length() == YYYY_MM.length()) {
            date = parse(dateStr, YYYY_MM);
        } else if (dateStr.length() == YYYY_MM_DD.length()) {
            date = parse(dateStr, YYYY_MM_DD);
        } else if (dateStr.length() == YYYY_MM_DD_HH_MM.length()) {
            date = parse(dateStr, YYYY_MM_DD_HH_MM);
        } else if (dateStr.length() == YYYY_MM_DD_HH_MM_SS.length()) {
            date = parse(dateStr, YYYY_MM_DD_HH_MM_SS);
        } else {
            date = parseCST(dateStr);
        }
        return date;
    }

    public static Date parseCST(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY, Locale.US);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return parse(dateStr, YYYY_MM_DD_HH_MM_SS);
        }
    }

    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        Date date = null;
        try {
            fmt.applyPattern(pattern);
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            try {
                fmt.applyPattern(YYYY_MM_DD_HH_MM);
                date = fmt.parse(dateStr);
            } catch (ParseException e1) {
                fmt.applyPattern(YYYY_MM_DD);
                try {
                    date = fmt.parse(dateStr);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return date;
    }

    public static String formatTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    public static String formatTear(Date date) {
        return format(date, YYYY);
    }

    public static String formatDate(Date date) {
        return format(date, YYYY_MM_DD);
    }

    public static String formatDateTime(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatDateTimeChina(Date date) {
        return format(date, CHINA_YYYY_MM_DD_HH_MM);
    }

    public static String format(String pattern) {
        return format(new Date(), pattern);
    }

    public static String longToDateTime(long millSec) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        fmt.applyPattern(YYYY_MM_DD_HH_MM_SS);
        Date date = new Date(millSec);
        return fmt.format(date);
    }

    public static String longToDateTime(long millSec, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        fmt.applyPattern(pattern);
        Date date = new Date(millSec);
        return fmt.format(date);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        fmt.applyPattern(pattern);
        return fmt.format(date);
    }

    /**
     * 获取现在的时间
     *
     * @return long类型的时间
     */
    public static long parseLong() {
        return System.currentTimeMillis();
    }

    public static long parseLong(Date date) {
        return date.getTime();
    }

    public static DateUtils getDateInstance() {
        if (date == null) {
            date = new DateUtils();
        }
        return date;
    }

    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 得到指定日期的一天的的最后时刻23:59:59
     *
     * @param date
     * @return
     */
    public static Date getFinallyDate(Date date) {
        if (date != null) {
            String temp = format.format(date);
            temp += " 23:59:59";
            try {
                return format1.parse(temp);
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 得到指定日期的一天的开始时刻00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {
        if (date != null) {
            String temp = format.format(date);
            temp += " 00:00:00";
            try {
                return format1.parse(temp);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 得到当前日期 格式为 八位例如：20130510
     *
     * @return
     */
    public static String getNowDate() {
        Calendar calendar = getCalendar();
        return getDateByCal(calendar);
    }

    public static int getNowDateInt() {
        return Integer.valueOf(getNowDate());
    }

    /**
     * 得到时分秒 格式为六位 例如：120525 （12点05分25秒）
     *
     * @return
     */
    public static String getNowTime() {
        Calendar calendar = getCalendar();
        return getTimeByCal(calendar);
    }

    public static int getNowTimeInt() {
        return Integer.valueOf(getNowTime());
    }

    /**
     * 根据输入的日期字符串 和 提前天数 ， 获得 指定日期提前几天的日期对象
     *
     * @return 指定日期倒推指定天数后的日期对象
     * @throws ParseException
     */
    public static Date getDate(Date date, int beforeDays)
            throws ParseException {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(new Date());
        theCa.add(Calendar.DATE, beforeDays * -1);
        return theCa.getTime();
    }

    /*
    public static void main(String[] args) {
        System.out.print(DateUtils.formatDateTimeChina(new Date()));
        System.out.print(DateUtils.format(YYYYMMDDHHMMSS));
    }*/

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date startTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取intervalMSeconds毫秒之前的Calendar
     *
     * @param intervalMSeconds
     * @return
     */
    public static Calendar getCalendarByMSeconds(long intervalMSeconds) {
        long mSeconds = DateUtils.parseLong() - intervalMSeconds;
        Date date = new Date(mSeconds);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 毫秒数转为Calendar
     *
     * @param now
     * @return
     */
    public static Calendar getCalendarByLong(long now) {
        Date date = new Date(now);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 获取intervalMSeconds毫秒之前的日期 格式为 八位 例如：20130510
     *
     * @return
     */
    public static String getDateByMSeconds(long intervalMSeconds) {
        Calendar calendar = getCalendarByMSeconds(intervalMSeconds);
        return getDateByCal(calendar);
    }

    /**
     * 毫秒数转为日期 格式为 八位 例如：20130510
     *
     * @return
     */
    public static String getDateByLong(long now) {
        Calendar calendar = getCalendarByLong(now);
        return getDateByCal(calendar);
    }

    private static String getDateByCal(Calendar calendar) {
        buffer.delete(0, buffer.capacity());
        buffer.append(calendar.get(Calendar.YEAR));
        int monday = calendar.get(Calendar.MONDAY) + 1;
        if (monday < 10) {
            buffer.append(ZERO);
        }
        buffer.append(monday);
        int day = calendar.get(Calendar.DATE);
        if (day < 10) {
            buffer.append(ZERO);
        }
        buffer.append(day);
        return buffer.toString();
    }

    /**
     * 获取intervalMSeconds毫秒之前的日期 格式为 八位 例如：20130510
     *
     * @return
     */
    public static int getDateIntByMSeconds(long intervalMSeconds) {
        return Integer.valueOf(getDateByMSeconds(intervalMSeconds));
    }

    /**
     * 毫秒数转为日期 格式为 八位 例如：20130510 格式为 八位 例如：20130510
     *
     * @return
     */
    public static int getDateIntByLong(long now) {
        return Integer.valueOf(getDateByLong(now));
    }

    /**
     * 获取intervalMSeconds毫秒之前的时分秒 格式为六位 例如：120525 （12点05分25秒）
     *
     * @param intervalMSeconds
     * @return
     */
    public static String getTimeByMSeconds(long intervalMSeconds) {
        Calendar calendar = getCalendarByMSeconds(intervalMSeconds);
        return getTimeByCal(calendar);
    }

    /**
     * 毫秒数转为时分秒 格式为六位 例如：120525 （12点05分25秒）
     *
     * @param now
     * @return
     */
    public static String getTimeByLong(long now) {
        Calendar calendar = getCalendarByLong(now);
        return getTimeByCal(calendar);
    }

    private static String getTimeByCal(Calendar calendar) {
        buffer.delete(0, buffer.capacity());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            buffer.append(ZERO);
        }
        buffer.append(hour);
        int minute = calendar.get(Calendar.MINUTE);
        if (minute < 10) {
            buffer.append(ZERO);
        }
        buffer.append(minute);
        int second = calendar.get(Calendar.SECOND);
        if (second < 10) {
            buffer.append(ZERO);
        }
        buffer.append(second);
        return buffer.toString();
    }

    /**
     * 获取intervalMSeconds毫秒之前的时分秒 格式为六位 例如：120525 （12点05分25秒）
     *
     * @param intervalMSeconds
     * @return
     */
    public static int getTimeIntByMSeconds(long intervalMSeconds) {
        return Integer.valueOf(getTimeByMSeconds(intervalMSeconds));
    }

    /**
     * 毫秒数转为时分秒 格式为六位 例如：120525 （12点05分25秒）
     *
     * @param now
     * @return
     */
    public static int getTimeIntByLong(long now) {
        return Integer.valueOf(getTimeByLong(now));
    }

    /**
     * 传入日期dateInt，返回daysToAdd天之后的日期
     *
     * @param dateInt
     * @param daysToAdd
     * @return
     */
    public static int addDate(int dateInt, int daysToAdd) {
        Date date = parse(dateInt + "", YYYYMMDD);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, daysToAdd);
        String nextDate = getDateByCal(cal);
        return Integer.valueOf(nextDate);
    }

    /**
     * 数字日期格式化
     *
     * @param transdate
     * @param transtime
     * @return
     */
    public static String getTransDateTime(Integer transdate, Integer transtime) {
        if (transdate == null) {
            return null;
        }
        if (transtime == null) {
            transtime = 0;
        }
        String year = String.valueOf(transdate / 10000);
        String month = String.valueOf((transdate % 10000) / 100);
        String day = String.valueOf(transdate % 100);
        String hour = String.valueOf(transtime / 10000);
        String minute = String.valueOf((transtime % 10000) / 100);
        String second = String.valueOf(transtime % 100);
        month = putZero(month);
        day = putZero(day);
        hour = putZero(hour);
        minute = putZero(minute);
        second = putZero(second);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    private static String putZero(String str) {
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }
    /**
     * 8位日期字符串转为10位日期字符串 例如 20180101 结果2018-01-01
     *
     * @param
     * @param
     * @return
     */
    public static String getDateTenString(String date) {
    	StringBuffer sb=new StringBuffer(date);
		StringBuffer buffer = sb.insert(4, "-");
		StringBuffer insert = buffer.insert(7, "-");
        return new String(insert);
    }
    
    public static void main(String[] args) {
        System.out.println(getDateByLong(1511490602523L));
        System.out.println(getTimeByLong(1511490602523L));
        System.out.println(getDateIntByLong(1511490602523L));
        System.out.println(getTimeIntByLong(1511490602523L));
    }
}