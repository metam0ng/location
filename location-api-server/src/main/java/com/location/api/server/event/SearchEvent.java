package com.location.api.server.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class SearchEvent extends ApplicationEvent {
    private String keyword;
    public SearchEvent(Object source) {
        super(source);
    }

    public SearchEvent(Object source,
                       Clock clock) {
        super(source, clock);
    }

    public SearchEvent(Object source,
                       String keyword) {
        super(source);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }
}
