package com.location.external.client.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@Slf4j
@ConfigurationProperties(prefix = "client.external")
public class LocationExternalClientProperties {

    private String kakaoUrl;

    private String kakaoKey;

    private String naverUrl;

    private String naverId;

    private String naverSecret;

    private Duration connectTimeout;

    private Duration readTimeout;

}
