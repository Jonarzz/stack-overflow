package io.github.jonarzz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestedController.class)
@AutoConfigureMockMvc
public class TestWithoutConfiguration {

    @Test
    void fails() {

    }

}
