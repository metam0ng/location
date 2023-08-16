package com.location.common.holder;

public interface ExceptionCountHolder {
    int count();
    void increase();
    boolean isEqualOrOver(int count);
}
