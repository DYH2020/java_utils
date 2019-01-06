package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by daiyihua
 */
public class FormatUtil {

    /**
     * 保留两位有效数字
     */
    public static String decimalFormat(BigDecimal value){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

    /**
     * 保留两位有效数字
     */
    public static String decimalFormat(Double value){
        DecimalFormat df = new DecimalFormat("0.00");
        if (value==null|| value == 0 )
            return "0.00";
        return df.format(value);
    }

    /**
     * 时间转换(专用list列表里面)
     */
    public static String createTimeFormatList(String value){
        if (value == null || value.equals(""))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(value);
    }

    /**
     * 时间转换(专用list列表里面)
     */
    public static String createTimeFormatList(Date value){
        if (value == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(value);
    }

    /**
     * 时间转换（专用详情里面）
     */
    public static String createTimeFormatDetail(String value){
        if (value == null || value.equals(""))
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(value);
    }

    /**
     * 时间转换（专用详情里面）
     */
    public static String createTimeFormatDetail(Date value){
        if (value == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(value);
    }



    /**
     * 时间转换（得到日期）
     */
    public static String formatDate(Date value){
        if (value == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(value);
    }

    /**
     * 时间转换（得到日期）
     */
    public static String formatDate(String value){
        if (value == null || value.equals(""))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(value);
    }



}
