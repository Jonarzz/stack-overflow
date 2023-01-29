package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.slf4j.*;

class SomeClassTest {

    @Test
    void mockTest() {
        var tested = new SomeClass();
        var mockedValue = "1234";
        try (var mockStatic = Mockito.mockStatic(MDC.class)) {
            mockStatic.when(() -> MDC.get("correlation-id"))
                      .thenReturn(mockedValue);

            var result = tested.getFromMdc();

            assertEquals(mockedValue, result);
        }
    }

    @Test
    void putTest() {
        var tested = new SomeClass();
        var correlationId = "1234";
        MDC.put("correlation-id", correlationId);

        var result = tested.getFromMdc();

        assertEquals(correlationId, result);
    }
}