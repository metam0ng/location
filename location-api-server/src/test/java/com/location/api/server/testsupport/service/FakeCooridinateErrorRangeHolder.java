package com.location.api.server.testsupport.service;

import com.location.common.holder.CooridinateErrorRangeHolder;
import org.springframework.stereotype.Component;

@Component
public class FakeCooridinateErrorRangeHolder implements CooridinateErrorRangeHolder {
    @Override
    public double errorRange() {
        return 0.0001;
    }

    @Override
    public double errorRange(double range) {
        return range;
    }
}
