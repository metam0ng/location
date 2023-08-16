package com.location.external.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Slf4j
public class KakaoExternalRestApiInterceptor implements ClientHttpRequestInterceptor {
    private LocationExternalClientProperties properties;
    public KakaoExternalRestApiInterceptor(LocationExternalClientProperties properties) {
        this.properties = properties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        headers.add("Authorization", properties.getKakaoKey());

        return execution.execute(request, body);
    }
}
