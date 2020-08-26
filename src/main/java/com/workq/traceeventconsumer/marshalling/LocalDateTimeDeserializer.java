package com.workq.traceeventconsumer.marshalling;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This instructs Jackson how to convert a {@link LocalDateTime} to a string in ISO-8601 format.
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public LocalDateTimeDeserializer(Class vc) {
        super(vc);
    }

    @SuppressWarnings("unused")
    public LocalDateTimeDeserializer() {
        this(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctx)
        throws IOException {
        return LocalDateTime.parse(parser.readValueAs(String.class));
    }


}

