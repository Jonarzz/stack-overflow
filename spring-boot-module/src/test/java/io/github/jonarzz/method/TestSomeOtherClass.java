package io.github.jonarzz.method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import io.github.jonarzz.ConfigurationClass;
import io.github.jonarzz.MyObject;
import io.github.jonarzz.SomeOtherClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.ObjectProvider;

public class TestSomeOtherClass {

    @Mock
    MyObject myObject;
    @Mock
    ObjectProvider<MyObject> myObjectObjectProvider;
    @InjectMocks
    ConfigurationClass configuration;

    SomeOtherClass someOtherClass;

    AutoCloseable openMocks;

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        doReturn(myObject).when(myObjectObjectProvider)
                          .getIfAvailable();
        someOtherClass = spy(configuration.getSomeOtherClass());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void test() {
        assertNotNull(someOtherClass);
    }
}
