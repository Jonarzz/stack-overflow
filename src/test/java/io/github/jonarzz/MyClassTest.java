package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class MyClassTest {

    MyClass myClass = new MyClass();

    @Test
    void shouldDoSth() throws Exception {
        var staticField = MyClass.class.getDeclaredField("MY_STATIC_BOOL");
        staticField.setAccessible(true);
        staticField.set(null, true);

        var result = myClass.doSomething();

        assertEquals("Success", result);
    }

}