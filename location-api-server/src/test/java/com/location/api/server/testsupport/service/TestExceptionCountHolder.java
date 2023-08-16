package com.location.api.server.testsupport.service;

import com.location.common.holder.ExceptionCountHolder;

public class TestExceptionCountHolder implements ExceptionCountHolder {

    private int count;

    public TestExceptionCountHolder(int count) {
        this.count = count;
    }

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
