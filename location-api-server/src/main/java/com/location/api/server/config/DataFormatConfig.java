package com.location.api.server.config;

import com.location.common.binder.LocalDateParamBinder;
import com.location.common.binder.LocalDateTimeParamBinder;
import com.location.common.binder.LocalTimeParamBinder;
import com.location.common.binder.YearMonthParamBinder;
import com.location.common.code.DescriptionCode;
import com.location.common.converter.DefaultDateTimeFormat;
import com.location.common.converter.DescriptionCodeJsonConverter;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Configuration
@EnableWebMvc
public class DataFormatConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateParamBinder());
        registry.addConverter(new LocalTimeParamBinder());
        registry.addConverter(new LocalDateTimeParamBinder());
        registry.addConverter(new YearMonthParamBinder());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder().build()));
        converters.add(new StringHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false) // SpringBoot default
                .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION) // SpringBoot default
                .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // SpringBoot default
                .serializerByType(LocalDate.class, new LocalDateSerializer(DefaultDateTimeFormat.DATE_FORMAT))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DefaultDateTimeFormat.DATE_FORMAT))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DefaultDateTimeFormat.DATE_TIME_FORMAT))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DefaultDateTimeFormat.DATE_TIME_FORMAT))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DefaultDateTimeFormat.TIME_FORMAT))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DefaultDateTimeFormat.TIME_FORMAT))
                .serializerByType(YearMonth.class, new YearMonthSerializer(DefaultDateTimeFormat.YEAR_MONTH_FORMAT))
                .deserializerByType(YearMonth.class, new YearMonthDeserializer(DefaultDateTimeFormat.YEAR_MONTH_FORMAT))
                .serializerByType(DescriptionCode.class, new DescriptionCodeJsonConverter.Serializer())
                .deserializerByType(Enum.class, new DescriptionCodeJsonConverter.Deserializer());
    }

}
