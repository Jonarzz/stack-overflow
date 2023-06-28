package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

class MyClassTest {

@Test
void notHelloTest() {
    try (var mocked = Mockito.mockStatic(MyClass.class, CALLS_REAL_METHODS)) {
        mocked.when(MyClass::something)
              .thenReturn("not hello");

        var result = MyClass.testAble();

        assertEquals("LoL", result);
    }
}

@Test
void helloTest() {
    try (var mocked = Mockito.mockStatic(MyClass.class, CALLS_REAL_METHODS)) {
        mocked.when(MyClass::something)
              .thenReturn("Hello");

        var result = MyClass.testAble();

        assertEquals("Wow", result);
    }
}
}