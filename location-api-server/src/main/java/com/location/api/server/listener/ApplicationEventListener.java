package com.location.api.server.listener;

import com.location.api.server.event.SearchEvent;
import com.location.api.server.service.command.KeywordCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationEventListener {

    private final KeywordCommandService keywordCommandService;

    @Async
    @EventListener
    public void exceptionEventListener(SearchEvent event) {
        keywordCommandService.increaseCount(event.getKeyword());
    }

}

