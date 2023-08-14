package com.location.common.binder;

import com.location.common.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class LocalTimeParamBinder implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        if (source.isEmpty()) {
            return null;
        }
        return DefaultDateTimeConverter.convertTime(source);
    }
}
