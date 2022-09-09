package io.github.jonarzz;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InjectedWithAnnotationTest {

    @Mock
    Config config;
    @InjectMocks
    ClassUnderTest underTest;

    @Test
    void test() {
        var configObj = new Object();
        when(config.getConfig(anyString())).thenReturn(configObj);

        underTest.method("some string");
    }
}