package com.location.external.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class LocationExternalClientConfig {

    private static final String EXTERNAL_CLIENT_MODE = "client.external.client-mode";

    @Configuration
    @ConditionalOnProperty(value = EXTERNAL_CLIENT_MODE, havingValue = "rest")
    @EnableConfigurationProperties(LocationExternalClientProperties.class)
    @ComponentScan("com.location.external.client.rest")
    static class LocationExternalRestClientConfig {

        @Bean
        public RestTemplate kakaoRestTemplate(LocationExternalClientProperties properties,
                                              TraceRestTemplateCustomizer restTemplateCustomizer) {
            return new LocationExternalRestTemplateProvider(properties, restTemplateCustomizer).getKakaoMapRestTemplate();
        }

        @Bean
        public RestTemplate naverRestTemplate(LocationExternalClientProperties properties,
                                              TraceRestTemplateCustomizer restTemplateCustomizer) {
            return new LocationExternalRestTemplateProvider(properties, restTemplateCustomizer).getNaverMapRestTemplate();
        }
    }

    @Configuration
    @ConditionalOnProperty(value = EXTERNAL_CLIENT_MODE, havingValue = "stub", matchIfMissing = true)
    @ComponentScan("com.location.external.client.stub")
    static class LocationExternalStubClientConfig {
    }

}
