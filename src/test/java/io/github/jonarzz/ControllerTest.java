package io.github.jonarzz;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

    @WebMvcTest(Controller.class)
    class ControllerTest {

        private static final IProcessor PROCESSOR = mock(IProcessor.class);

        @TestConfiguration
        static class InnerConfiguration {

            @Bean
            ProcessorFactory processorFactory() {
                ProcessorFactory processorFactory = mock(ProcessorFactory.class);
                when(processorFactory.getInstance())
                        .thenReturn(PROCESSOR);
                return processorFactory;
            }

        }

        @Autowired
        private MockMvc mockMvc;

        @Test
        void test1() throws Exception {
            String result = "this is a test";
            when(PROCESSOR.process(any()))
                    .thenReturn(result);

            mockMvc.perform(post("/test/test")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{}"))
                   .andDo(print())
                   .andExpect(status().is2xxSuccessful())
                   .andExpect(content().string(result));
        }

    }