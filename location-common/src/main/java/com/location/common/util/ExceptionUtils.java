package com.location.common.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ExceptionUtils {

    public static String simpleStackTraceString(final Throwable throwable) {
        return Arrays.stream(throwable.getStackTrace())
                .map(StackTraceElement::toString)
                .filter(ExceptionUtils::interestedTrace)
                .collect(Collectors.joining("\n"));
    }

    private static boolean interestedTrace(final String trace) {
        return (!trace.contains("\tat") && !trace.matches("^\\t\\.\\.\\. [0-9]+ more$"))
                || trace.contains("biz.riman")
                || trace.contains("Caused by: ");
    }

}
