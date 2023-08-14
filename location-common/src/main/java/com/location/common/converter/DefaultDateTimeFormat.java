package com.location.common.converter;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DefaultDateTimeFormat {

    private DefaultDateTimeFormat(){}

    public static final DateTimeFormatter DATE_FORMAT      = ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter TIME_FORMAT      = ofPattern("HH:mm:ss");

    public static final DateTimeFormatter DATE_TIME_FORMAT = ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter DATE_NONE_DASH_FORMAT      = ofPattern("yyyyMMdd");

    public static final DateTimeFormatter DATE_TIME_NONE_DASH_FORMAT = ofPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter YEAR_MONTH_NONE_DASH_FORMAT = ofPattern("yyyyMM");

    public static final DateTimeFormatter YEAR_MONTH_FORMAT = ofPattern("yyyy-MM");
}

