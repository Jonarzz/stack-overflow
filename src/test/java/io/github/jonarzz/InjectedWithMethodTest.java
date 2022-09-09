package io.github.jonarzz;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InjectedWithMethodTest {

    @Mock
    Config config;
    @InjectMocks
    ClassUnderTest underTest;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void test() {
        var configObj = new Object();
        when(config.getConfig(anyString())).thenReturn(configObj);

        underTest.method("some string");
    }
}