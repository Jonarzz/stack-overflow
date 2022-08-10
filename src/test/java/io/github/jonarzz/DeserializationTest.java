package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jonarzz.solution.FixedFilm;
import org.junit.jupiter.api.Test;

public class DeserializationTest {

    ObjectMapper objectMapper = new ObjectMapper()
            .setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY)
            .findAndRegisterModules();

    String json = """
            {
              "name": "name",
              "releaseDate": "2000-01-01",
              "description": "desc",
              "duration": 10,
              "rate": 1,
              "mpa": { "id": 3},
              "genres": [{ "id": 1}]
            }
            """;

    @Test
    void classFromQuestion() throws JsonProcessingException {
        var film = objectMapper.readValue(json, Film.class);

        assertNotNull(film);
    }

    @Test
    void filmWithConstructor() throws JsonProcessingException {
        var film = objectMapper.readValue(json, FixedFilm.class);

        assertNotNull(film);
    }

}
