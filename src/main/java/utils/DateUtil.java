package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期类
 */
public class DateUtil {
    private static final String[] WEEK_DAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    private static final String[] WEEK_DAYS_SHORT = {"日", "一", "二", "三", "四", "五", "六"};

    /**
     * 将时间戳转换成日期字符串格式
     *
     * @param timestamp 时间戳
     * @return
     */
    public static String convertTimestampToDateString(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将时间戳转换成日期字符串格式
     *
     * @param timestamp 时间戳
     * @param pattern   日期格式
     * @return
     */
    public static String convertTimestampToDateString(long timestamp, String pattern) {
        Date date = new Date(timestamp);

        if (StringHelper.isNullOrEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将时间戳转换成日期类型
     *
     * @param timestamp 时间戳
     * @return
     */
    public static Date convertTimestampToDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (StringHelper.isNullOrEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(date);
    }

    /**
     * 当前日期是星期几
     * @param calendar
     * @return
     */
    public static String getDayOfWeek(Calendar calendar) {
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }
        return WEEK_DAYS[index];
    }

    /**
     * 当前日期是星期（缩略）
     * @param calendar
     * @return
     */
    public static String getDayOfWeekShort(Calendar calendar) {
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }
        return WEEK_DAYS_SHORT[index];
    }

    public static Date parseDate(String str, String pattern) {
        if (StringHelper.isNullOrWhiteSpace(str)) {
            return null;
        }

        if (StringHelper.isNullOrWhiteSpace(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";    //默认日期格式
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            format.setLenient(false);
            return format.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convertToString(Date date){
        return convertToString(date, null);
    }

    public static String convertToString(Date date, String pattern){
        if(StringHelper.isNullOrEmpty(pattern)){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
