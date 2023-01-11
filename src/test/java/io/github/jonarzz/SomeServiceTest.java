package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

class SomeServiceTest {

    @Test
    void testWithValue() {
        var id = "id";
        var mockedValue = "test value";
        var someConfig = new SomeConfig(mockedValue);
        var someService = new SomeService();
        try (MockedStatic<UtilClass> dummy = Mockito.mockStatic(UtilClass.class)) {
            dummy.when(() -> UtilClass.getConfigForId(id))
                 .thenReturn(someConfig);

            var result = someService.doSth(id);

            assertEquals(mockedValue, result);
        }
    }

    @Test
    void testWithArgumentMatcher() {
        var mockedValue = "test value 2";
        var someConfig = new SomeConfig(mockedValue);
        var someService = new SomeService();
        try (MockedStatic<UtilClass> dummy = Mockito.mockStatic(UtilClass.class)) {
            dummy.when(() -> UtilClass.getConfigForId(any()))
                 .thenReturn(someConfig);

            var result = someService.doSth("some id");

            assertEquals(mockedValue, result);
        }
    }
}