package com.location.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
public class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("This is utility class.");
    }

    public static int getNowHalfYear() {
        return getHalfYear(LocalDate.now());
    }

    public static int getHalfYear(LocalDate localDate) {
        return (int) Math.ceil(localDate.getMonthValue() / 6.0);
    }

    public static int getHalfYear(LocalDateTime localDatetime) {
        return (int) Math.ceil(localDatetime.getMonthValue() / 6.0);
    }

    public static int getNowQuarter() {
        return getQuarter(LocalDate.now());
    }

    public static int getQuarter(LocalDate localDate) {
        return (int) Math.ceil(localDate.getMonthValue() / 3.0);
    }

    public static int getQuarter(LocalDateTime localDatetime) {
        return (int) Math.ceil(localDatetime.getMonthValue() / 3.0);
    }

    public static boolean isEqualsYear(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.getYear() == localDateTime2.getYear();
    }

    public static LocalDateTime getMaxDateTime(List<LocalDateTime> datetimeList) {
        List<LocalDateTime> targetDatetimeList = datetimeList;
        targetDatetimeList.removeAll(Collections.singletonList(null));
        return Collections.max(targetDatetimeList);
    }



}
