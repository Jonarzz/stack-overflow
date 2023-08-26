package io.github.jonarzz;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.assertj.core.api.*;
import org.junit.jupiter.api.*;

class TestedClassVanillaTest {

    SomeService someService = mock(SomeService.class);
    TestedClass testedClass = new TestedClass(someService);

    @Test
    void test() {
        var exception = new RuntimeException("test error");
        when(someService.doStuff())
                .thenThrow(exception);

        ThrowableAssert.ThrowingCallable methodCall = () -> {
            new Thread(() -> testedClass.execute())
                    .start();
        };

        assertThatNoException()
                .isThrownBy(methodCall);
    }
}