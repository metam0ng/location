package com.location.api.server.testsupport;

import com.location.common.code.DescriptionCode;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.restdocs.snippet.Attributes;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.restdocs.snippet.Attributes.key;

public class ApiDocumentFormatGenerator {

    //----------------------------------------------------------------------------------------------------------------//

    public static Attributes.Attribute getRequiredTrue() {
        return key("required").value("true");
    }

    public static Attributes.Attribute getRequiredFalse() {
        return key("required").value("false");
    }

    public static Attributes.Attribute getEmptyFormat() {
        return key("format").value("");
    }

    public static Attributes.Attribute getBooleanFormat() {
        return key("format").value(generate("true", "false"));
    }

    public static Attributes.Attribute getMonthFormat() {
        return key("format").value("yyyy-MM");
    }

    public static Attributes.Attribute getDateFormat() {
        return key("format").value("yyyy-MM-dd");
    }

    public static Attributes.Attribute getDateTimeFormat() {
        return key("format").value("yyyy-MM-dd HH:mm:ss");
    }

    public static Attributes.Attribute getCircuitBreakerStateFormat() { return key("format").value(generate(CircuitBreaker.State.class)); }


    public static <E extends Enum<E>> String generate(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining(" +\n"));
    }

    public static <E extends Enum<E>> String generate(DescriptionCode[] descriptionCodes) {
        return Arrays.stream(descriptionCodes)
                .map(e -> String.format("%s : %s", e.getCode() ,e.getDescription()))
                .collect(Collectors.joining(" +\n"));
    }

    public static String generate(String... values) {
        return Arrays.stream(values)
                .collect(Collectors.joining(" +\n"));
    }

    //----------------------------------------------------------------------------------------------------------------//
}