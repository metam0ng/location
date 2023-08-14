package com.location.common.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalTime;

@JsonComponent
public class LocalTimeJsonConverter {

    public static class Serializer extends JsonSerializer<LocalTime> {

        @Override
        public Class<LocalTime> handledType() {
            return LocalTime.class;
        }

        @Override
        public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString(DefaultDateTimeConverter.convertTime(localTime));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalTime> {

        @Override
        public Class<LocalTime> handledType() {
            return LocalTime.class;
        }

        @Override
        public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return DefaultDateTimeConverter.convertTime(jsonParser.getText());
        }

    }

}
