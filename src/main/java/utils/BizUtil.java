package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author: Walker
 * @date: 2019/1/3
 */
public final class BizUtil {
    /**
     * convert cent to yuan
     * @param cent cent
     * @return value of yuan
     */
    public static double cent2Yuan(int cent) {
        BigDecimal v1 = new BigDecimal(String.valueOf(cent));
        BigDecimal v2 = new BigDecimal("100");
        BigDecimal result = v1.divide(v2, 2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    /**
     * convert yuan to cent
     * @param yuan yuan
     * @return value of cent
     */
    public static double yuan2Cent(double yuan) {
        BigDecimal v1 = new BigDecimal(String.valueOf(yuan));
        BigDecimal v2 = new BigDecimal("100");
        BigDecimal result = v1.multiply(v2);
        return result.intValue();
    }
}
