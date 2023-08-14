package com.location.common.holder;

import org.springframework.stereotype.Component;

@Component
public class SystemCountHolder implements CountHolder {
    @Override
    public Long one() {
        return 1L;
    }

    @Override
    public Long increase(Long target) {
        return target+1L;
    }
}
