package utils;

import java.util.regex.Pattern;

/**
 * 手机号码校验类
 * 目前的号码段截止至 2016年12月
 */
public class PhoneNumChecker {
    /**
     * 中国电信号码格式正则表达式
     * 中国电信号码段：133,153,180,181,189,177,1700,173
     */
    private static final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";

    /**
     * 中国联通号码格式正则表达式
     * 中国联通号码段：130,131,132,155,156,185,186,145,176,1707,1708,1709
     */
    private static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";

    /**
     * 中国移动号码格式正则表达式
     * 中国移动号码段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     */
    private static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";

    /**
     * 手机号格式正则表达式
     */
    private static final String MOBILE_NUM_PATTERN=new StringBuilder(300)
            .append(CHINA_MOBILE_PATTERN)
            .append("|")
            .append(CHINA_TELECOM_PATTERN)
            .append("|")
            .append(CHINA_UNICOM_PATTERN)
            .toString();

    /**
     * 手机号码校验
     * @param mobile
     * @return
     */
    public static boolean isPhoneNum(String mobile){
        return match(MOBILE_NUM_PATTERN, mobile);
    }


    /**
     * 电信手机号码校验
     * @param mobile
     * @return
     */
    public static boolean isChinaTelecomPhoneNum(String mobile) {
        return match(CHINA_TELECOM_PATTERN, mobile);
    }

    /**
     * 联通手机号码校验
     * @param mobile
     * @return
     */
    public static boolean isChinaUnicomPhoneNum(String mobile) {
        return  match(CHINA_UNICOM_PATTERN, mobile);
    }

    /**
     * 移动手机号码校验
     * @param mobile
     * @return
     */
    public static boolean isChinaMobilePhoneNum(String mobile) {
        return  match(CHINA_MOBILE_PATTERN,mobile);
    }

    /**
     *
     * @param regex
     * @param mobile
     * @return
     */
    private static boolean match(String regex, String mobile) {
        return Pattern.matches(regex, mobile);
    }

}
