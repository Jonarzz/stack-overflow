package io.github.jonarzz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import test.TestConfiguration;

@SpringBootTest(classes = {TestedController.class, TestConfiguration.class})
@AutoConfigureMockMvc
public class TestWithTestConfigurationButWithoutMockBean {

    @Test
    void fails() {

    }

}
