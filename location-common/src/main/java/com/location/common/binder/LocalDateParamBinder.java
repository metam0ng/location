package com.location.common.binder;

import com.location.common.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateParamBinder implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (source.isEmpty()) {
            return null;
        }
        return DefaultDateTimeConverter.convertDate(source);
    }
}
