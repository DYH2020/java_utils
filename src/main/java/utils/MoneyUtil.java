package utils;

import java.math.BigDecimal;

public final class MoneyUtil {
    private MoneyUtil() {
        throw new UnsupportedOperationException("U can't instantiate me...");
    }

    /**
     * 元转分，double to int某些情况下会丢失精度，所以用BigDecimal.
     * @param number
     * @return
     */
    public static int yuan2Cent(double number) {
        BigDecimal v1 = new BigDecimal(String.valueOf(number));
        BigDecimal v2 = new BigDecimal("100");
        BigDecimal result = v1.multiply(v2);
        return result.intValue();
    }
}
