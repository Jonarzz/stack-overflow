package io.github.jonarzz;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.assertj.core.api.ThrowableAssert.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

@SpringBootTest
class TestedClassSpringTest {

    @MockBean
    SomeService someService;
    @Autowired
    TestedClass testedClass;

    @Test
    void test() {
        var exception = new RuntimeException("test error");
        when(someService.doStuff())
                .thenThrow(exception);

        ThrowingCallable methodCall = () -> testedClass.execute();

        assertThatNoException()
                .isThrownBy(methodCall);
    }
}