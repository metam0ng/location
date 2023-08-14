package com.location.common.binder;

import com.location.common.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class LocalDateTimeParamBinder implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        if (source.isEmpty()) {
            return null;
        }
        return DefaultDateTimeConverter.convertDateTime(source);
    }
}
