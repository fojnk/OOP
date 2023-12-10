package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Json {
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();
    private static final TypeReference<List<Note>> ref = new TypeReference<List<Note>>() {};

    private static ObjectMapper getDefaultObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static List<Note> readNotes(File filename) throws IOException {
        return objectMapper.readValue(filename, ref);
    }

    public static void writeNotes(File filename, List<Note> notes) throws IOException {
        objectMapper.writeValue(filename, notes);
    }
}