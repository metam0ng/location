package com.location.common.util;

import java.math.BigDecimal;

public class DataValueConvertUtils {

    private DataValueConvertUtils() {}

    public static BigDecimal stringToBigDecimal(String strValue) {
        return new BigDecimal(regex(strValue).isEmpty() ? "0" : regex(strValue));
    }

    public static Integer doubleToInteger(Double doubleValue) {
        long longValue = Math.round(doubleValue);
        return (int) longValue;
    }

    public static Long stringToLong(String strValue) {
        return Long.valueOf(regex(strValue));
    }

    public static Double stringToDouble(String strValue) {
        return Double.valueOf(strValue);
    }

    private static String regex(String strValue) {
        return strValue.replaceAll("[^0-9]", "");
    }

    public static String strRegex(String strValue) {
        return strValue.replaceAll("[^0-9]", "0");
    }
}
