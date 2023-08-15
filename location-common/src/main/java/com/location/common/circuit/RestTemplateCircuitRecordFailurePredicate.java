package com.location.common.circuit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;

import java.util.function.Predicate;

@Slf4j
public class RestTemplateCircuitRecordFailurePredicate implements Predicate<Throwable> {
    //true 를 리턴하면 Fail 로 기록됨.

    @Override
    public boolean test(Throwable throwable) {
        // 4XX 클라이언트 에러는 fail로 기록하지 않음
        log.info("================================ isTrue : {}", throwable instanceof HttpClientErrorException);
        if (throwable instanceof HttpClientErrorException) {
            return false;
        }

        // 그 외에 에러는 모두 failure로 기록함(HttpServerErrorException, connection, timeout, IOException 등)
        return true;
    }
}
