package io.github.jonarzz;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SomeTest {

    @Spy
    private Service1 service1;
    @Spy
    private Service2 service2;
    @InjectMocks
    private MainService mainService;

    @BeforeEach
    public void startUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        mainService.start();

        verify(service1)
                .execute();
        verify(service2)
                .execute();
    }

}
