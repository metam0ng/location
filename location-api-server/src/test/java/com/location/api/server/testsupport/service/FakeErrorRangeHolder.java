package com.location.api.server.testsupport.service;

import com.location.common.holder.ErrorRangeHolder;
import org.springframework.stereotype.Component;

@Component
public class FakeErrorRangeHolder implements ErrorRangeHolder {
    @Override
    public double errorRange() {
        return 0.0001;
    }

    @Override
    public double errorRange(double range) {
        return range;
    }
}
