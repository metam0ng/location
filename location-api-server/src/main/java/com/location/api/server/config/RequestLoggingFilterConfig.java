package com.location.api.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeHeaders(true);         // 헤더정보를 로그에 포함한다.
        loggingFilter.setIncludeQueryString(true);     // 쿼리 문자열을 로그 메세지에 포함한다.
        loggingFilter.setIncludePayload(true);         // request내용을 로그에 포함한다.
        loggingFilter.setIncludeClientInfo(true);      // 클라이언트 주소와 세션 ID를 로그 메세지에 포함한다.
        loggingFilter.setMaxPayloadLength(1000);       // 로그의 최대 길이을 설정한다.
        loggingFilter.setBeforeMessagePrefix("RequestLoggingFilter > Before : ");
        loggingFilter.setBeforeMessageSuffix("");
        loggingFilter.setAfterMessagePrefix("RequestLoggingFilter > After : ");
        loggingFilter.setAfterMessageSuffix("");
        return loggingFilter;
    }
}
