package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作工具类
 */
public class DateUtil {


    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 格式化日期与实践
     * @param timestamp
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatDatetime(long timestamp){
        return datetimeFormat.format(new Date(timestamp));
    }

    /**
     * 格式化日期
     * @param timestamp
     * @return "yyyy-MM-dd"
     */
    public static String formatDate(long timestamp){
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * 格式化实践
     * @param timestamp
     * @return "HH:mm:ss"
     */
    public static String formatTime(long timestamp){
        return timeFormat.format(new Date(timestamp));
    }

    /**
     * 获取当前日期和时间
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getCurrentDatetime(){
        return datetimeFormat.format(new Date());
    }

    /**
     * 获取当前日期
     * @return "yyyy-MM-dd"
     */
    public static String getCurrentDate(){
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前时间
     * @return "HH:mm:ss"
     */
    public static String getCurrentTime(){
        return timeFormat.format(new Date());
    }

    /**
     * 解析日期和时间
     * @param str
     * @return Date
     */
    public static Date parseDatetime(String str){
        Date date = null;
        try {
            date = datetimeFormat.parse(str);
        }catch (ParseException e){
            logger.error("解析日期字符串出错！格式：yyyy-MM-dd HH:mm:ss",e);
        }
        return date;
    }

    /**
     * 解析日期
     * @param str
     * @return Date
     */
    public static Date parseDate(String str) {
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：yyyy-MM-dd", e);
        }
        return date;
    }

    /**
     * 解析时间
     * @param str
     * @return Date
     */
    public static Date parseTime(String str) {
        Date date = null;
        try {
            date = timeFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：HH:mm:ss", e);
        }
        return date;
    }
}
