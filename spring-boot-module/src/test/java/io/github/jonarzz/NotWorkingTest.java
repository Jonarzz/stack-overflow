package io.github.jonarzz;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

@SpringBootTest
public class NotWorkingTest {

    @SpyBean
    MyRepository repository;

    @Test
    void test() {
        var id = 1L;
        doThrow(new RuntimeException("first"))
                .doCallRealMethod()
                .doThrow(new RuntimeException("second"))
                .when(repository)
                .findById(id);

        repository.findById(id);
    }
}
