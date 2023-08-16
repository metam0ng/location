package com.location.api.server.testsupport.service;

import com.location.common.holder.CountHolder;

public class TestCountHolder implements CountHolder {
    @Override
    public Long one() {
        return 1L;
    }

    @Override
    public Long increase(Long target) {
        return target+1L;
    }
}
