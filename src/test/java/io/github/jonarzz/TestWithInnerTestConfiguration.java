package io.github.jonarzz;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestWithInnerTestConfiguration {

    @Configuration
    @Import(TestedApplication.class)
    static class InnerConfiguration {

        @Bean
        TestedServiceInterface service(MockedUtilInterface mockedUtil) {
            return () -> mockedUtil.value() + " - inner value";
        }

    }

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MockedUtilInterface mockedUtil;

    @Test
    void test() throws Exception {
        when(mockedUtil.value())
                .thenReturn(12345);

        mockMvc.perform(get("/test"))
               .andExpect(content().string("12345 - inner value"));
    }

}
