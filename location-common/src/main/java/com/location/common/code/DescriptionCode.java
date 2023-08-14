package com.location.common.code;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface DescriptionCode {

    default String getCode() {
        if (this instanceof Enum) {
            return ((Enum) this).name();
        } else {
            return null;
        }
    }

    @JsonProperty("text")
    String getDescription();

    static DescriptionCode toIdentityDescriptionCode(String code) {
        return new DescriptionCode() {
            public String getValue() {
                return code;
            }
            @Override
            public String getDescription() {
                return code;
            }
        };
    }

}
