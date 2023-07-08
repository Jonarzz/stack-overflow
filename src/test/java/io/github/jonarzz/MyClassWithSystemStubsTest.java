package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import uk.org.webcompere.systemstubs.environment.*;
import uk.org.webcompere.systemstubs.jupiter.*;

@ExtendWith(SystemStubsExtension.class)
class MyClassWithSystemStubsTest {

    @SystemStub
    EnvironmentVariables environment;

    @Test
    void theTest() {
        environment.set("foo", "true");
        MyClass myClass = new MyClass();

        var result = myClass.doSomething();

        assertEquals("Success", result);
    }
}