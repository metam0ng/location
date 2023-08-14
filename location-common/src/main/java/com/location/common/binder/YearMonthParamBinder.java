package com.location.common.binder;

import com.location.common.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.YearMonth;

public class YearMonthParamBinder implements Converter<String, YearMonth> {

    @Override
    public YearMonth convert(String source) {
        if (source.isEmpty()) {
            return null;
        }
        return DefaultDateTimeConverter.convertYearMonth(source);
    }
}
