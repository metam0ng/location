package com.location.common.holder;

public class SystemExceptionCountHolder implements ExceptionCountHolder {

    private int count;

    public SystemExceptionCountHolder(int count) {
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
