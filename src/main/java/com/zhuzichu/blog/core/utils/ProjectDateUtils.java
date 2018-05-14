package com.zhuzichu.blog.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关工具类
 */
public class ProjectDateUtils {
    private static final Logger logger = LoggerFactory.getLogger(ProjectDateUtils.class);

    public static final String DF_YYYYMMDD = "yyyyMMdd";
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DF_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DF_YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DF_HHMMSS = "HHmmss";


    /**
     * 将日期转换成字符串
     *
     * @param date
     * @param format
     *
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            throw new IllegalArgumentException("Param date is null!");
        }
        if (StringUtils.isBlank(format)) {
            throw new IllegalArgumentException("Param format is blank!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将日期转换成yyyyMMddHHmmss字符串
     *
     * @param date
     *
     * @return
     */
    public static String format14(Date date) {
        return format(date, DF_YYYYMMDDHHMMSS);
    }

    public static String format8(Date date) {
        return format(date, DF_YYYYMMDD);
    }

    public static String format10(Date date) {
        return format(date, DF_YYYY_MM_DD);
    }

    public static Date parse8(String dateStr) throws ParseException {
        return parse(dateStr, DF_YYYYMMDD);
    }

    public static Date parse10(String dateStr) throws ParseException {
        return parse(dateStr, DF_YYYY_MM_DD);
    }

    /**
     * 返回当前日期 yyyyMMdd格式 字符串
     *
     * @return
     */
    public static String getNow8() {
        return format8(new Date());
    }

    /**
     * 返回当前日期 yyyyMMdd格式 字符串
     *
     * @return
     */
    public static String getNow10() {
        return format10(new Date());
    }

    /**
     * 获取当天0点 date对象
     * @return
     */
    public static Date getToday(){
        Date today=null;
        try {
            today =parse(getNow8(),DF_YYYYMMDD);
        } catch (ParseException e) {
            //unreachable
            logger.error("Parsing / Getting today's date failed.",e);
            today = new Date();
        }
        return today;
    }

    /**
     * 获取当年1月1日0点 date对象
     * @return
     */
    public static Date getNowYear(){
        Date year=null;
        try {
            String format = "yyyy";
            year =parse(format(new Date(),format),format);
        } catch (ParseException e) {
            //unreachable
            logger.error("Parsing / Getting year's date failed.",e);
            year = new Date();
        }
        return year;
    }

    /**
     * 获取当月1号0点 date对象
     * @return
     */
    public static Date getNowMonth(){
        Date month=null;
        try {
            String format = "yyyy-MM";
            month =parse(format(new Date(),format),format);
        } catch (ParseException e) {
            //unreachable
            logger.error("Parsing / Getting month's date failed.",e);
            month = new Date();
        }
        return month;
    }

    public static int getMonthSpace(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return result == 0 ? 1 : Math.abs(result);
    }

    public static int getYearSpace4Now(String date) throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        c1.setTime(new Date());
        c2.setTime(sdf.parse(date));
        int result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return result == 0 ? 1 : Math.abs(result);
    }

    public static Date parse(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    /**
     * @return 获取当前月第一天：
     */
    public static Date getFirstDateOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        try {
            return parse(format8(c.getTime()),DF_YYYYMMDD);
        } catch (ParseException e) {
            logger.error("Parsing first day of current month is faile.",e);
            return null;
        }

    }

    /**
     * @return 获取下月第一天：
     */
    public static Date getFirstDateOfNextMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        try {
            return parse(format8(c.getTime()),DF_YYYYMMDD);
        } catch (ParseException e) {
            logger.error("Parsing first day of current month is faile.",e);
            return null;
        }

    }

    /**
     * @return 获取当前月最后一天
     */
    public static Date getListDateOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        try {
            return parse(format8(c.getTime()),DF_YYYYMMDD);
        } catch (ParseException e) {
            logger.error("Parsing first day of current month is faile.",e);
            return null;
        }
    }

    /**
     * @return 当月的总天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 计算时间间隔
     *
     * @param fDate 上次时间
     * @param oDate 本次时间
     * @return 精确到天
     */
    public static int getIntervalDays(Date fDate, Date oDate) {
        if (fDate == null || oDate == null) {
            return -1;
        }
        try {
            fDate = parse(format8(fDate),DF_YYYYMMDD);
            oDate = parse(format8(oDate), DF_YYYYMMDD);
        } catch (ParseException e) {
            logger.error("Parsing interval days is faile.", e);
        }
        long nd = 1000*24*60*60;//一天的毫秒数
        //获得两个时间的毫秒时间差异
        long diff = oDate.getTime() - fDate.getTime();
        Long day = diff/nd;//计算差多少天
        return day.intValue();

    }

    /**
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDate8(Date date){
        try {
            return parse8(format8(date));
        } catch (ParseException e) {
            //unreachable
        }
        return null;
    }

    /**
     * 时间加days天
     * @param date
     * @param days
     * @return
     */
    public static Date addDate(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 日期加法运算
     *
     * @param sourceDate
     * @param months       增加的月数，可为负数
     * @return
     */
    public static Date addMonths(Date sourceDate, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
}
