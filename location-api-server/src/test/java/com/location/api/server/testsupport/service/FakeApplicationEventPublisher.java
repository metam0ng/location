package com.location.api.server.testsupport.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class FakeApplicationEventPublisher implements ApplicationEventPublisher {
    @Override
    public void publishEvent(ApplicationEvent event) {
    }

    @Override
    public void publishEvent(Object event) {
    }
}
