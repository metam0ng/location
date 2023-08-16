package com.location.common.holder;

import org.springframework.stereotype.Component;

@Component
public class SystemCooridinateErrorRangeHolder implements CooridinateErrorRangeHolder {
    @Override
    public double errorRange() {
        return 0.001;
    }

    @Override
    public double errorRange(double range) {
        return range;
    }
}
