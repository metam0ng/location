package com.location.common.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.YearMonth;

@JsonComponent
public class YearMonthJsonConverter {

    private YearMonthJsonConverter() {
    }

    public static class Serializer extends JsonSerializer<YearMonth> {
        @Override
        public void serialize(YearMonth yearMonth, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(DefaultDateTimeConverter.convertYearMonth(yearMonth));
        }
    }

    public static class Deserializer extends JsonDeserializer<YearMonth> {
        @Override
        public YearMonth deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return DefaultDateTimeConverter.convertYearMonth(jsonParser.getText());
        }
    }

    public static class NonDashSerializer extends JsonSerializer<YearMonth> {
        @Override
        public void serialize(YearMonth yearMonth, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(DefaultDateTimeConverter.convertNoneDashMonth(yearMonth));
        }
    }

    public static class NonDashDeserializer extends JsonDeserializer<YearMonth> {
        @Override
        public YearMonth deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return DefaultDateTimeConverter.convertNoneDashMonth(jsonParser.getText());
        }
    }
}
