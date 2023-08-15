package com.location.common.holder;

import org.springframework.stereotype.Component;

@Component
public class SystemErrorRangeHolder implements ErrorRangeHolder {
    @Override
    public double errorRange() {
        return 0.0001;
    }

    @Override
    public double errorRange(double range) {
        return range;
    }
}
