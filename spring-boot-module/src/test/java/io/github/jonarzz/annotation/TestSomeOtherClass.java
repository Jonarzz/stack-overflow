package io.github.jonarzz.annotation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import io.github.jonarzz.ConfigurationClass;
import io.github.jonarzz.MyObject;
import io.github.jonarzz.SomeOtherClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;

@ExtendWith(MockitoExtension.class)
public class TestSomeOtherClass {

    @Mock
    MyObject myObject;
    @Mock
    ObjectProvider<MyObject> myObjectObjectProvider;
    @InjectMocks
    ConfigurationClass configuration;

    SomeOtherClass someOtherClass;

    @BeforeEach
    public void setup() {
        doReturn(myObject).when(myObjectObjectProvider)
                          .getIfAvailable();
        someOtherClass = spy(configuration.getSomeOtherClass());
    }

    @Test
    void test() {
        assertNotNull(someOtherClass);
    }
}
