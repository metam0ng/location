package com.location.common.holder;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SystemExceptionCountHolder implements ExceptionCountHolder {

    private int count;

    @Override
    public int count() {
        return this.count;
    }

    @Override
    public void increase() {
        this.count++;
    }

    @Override
    public boolean isEqualOrOver(int count) {
        return this.count >= count;
    }
}
