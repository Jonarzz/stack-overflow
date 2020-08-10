package io.github.jonarzz;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import test.TestConfiguration;

@SpringBootTest(classes = {TestedController.class, TestConfiguration.class})
@AutoConfigureMockMvc
public class TestWithTestConfiguration {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MockedUtilInterface mockedUtil;

    @Test
    void test() throws Exception {
        when(mockedUtil.value())
                .thenReturn(100);

        mockMvc.perform(get("/test"))
               .andExpect(content().string("100 - rest service from component scan value"));
    }

}
