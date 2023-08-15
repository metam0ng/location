package com.location.external.client.config;

import com.location.common.converter.LocalDateJsonConverter;
import com.location.common.converter.LocalDateTimeJsonConverter;
import com.location.common.converter.LocalTimeJsonConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateCustomizer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Getter
public class LocationExternalRestTemplateProvider {

    private final RestTemplate kakaoMapRestTemplate;
    private final RestTemplate naverMapRestTemplate;

    public LocationExternalRestTemplateProvider(LocationExternalClientProperties properties,
                                                TraceRestTemplateCustomizer restTemplateCustomizer) {
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .registerModule(new SimpleModule()
                        .addSerializer(LocalDate.class, new LocalDateJsonConverter.Serializer())
                        .addDeserializer(LocalDate.class, new LocalDateJsonConverter.Deserializer())
                        .addSerializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Serializer())
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeJsonConverter.Deserializer())
                        .addSerializer(LocalTime.class, new LocalTimeJsonConverter.Serializer())
                        .addDeserializer(LocalTime.class, new LocalTimeJsonConverter.Deserializer())
                );

        kakaoMapRestTemplate =  new RestTemplateBuilder()
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .interceptors(new KakaoExternalRestApiInterceptor())
                .build();

        naverMapRestTemplate =  new RestTemplateBuilder()
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .interceptors(new NaverExternalRestApiInterceptor())
                .build();

        if (restTemplateCustomizer == null) {
            log.warn("TraceRestTemplateCustomizer not configured.");
        } else {
            log.warn("TraceRestTemplateCustomizer is configured.");
            restTemplateCustomizer.customize(kakaoMapRestTemplate);
            restTemplateCustomizer.customize(naverMapRestTemplate);
        }

    }
}
