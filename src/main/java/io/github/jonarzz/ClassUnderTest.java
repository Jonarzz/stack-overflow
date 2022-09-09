package io.github.jonarzz;

public class ClassUnderTest {

    private Config config;

    public void method(String str) {
        config.getConfig("aString".concat(".").concat("anotherString"));
    }

}
