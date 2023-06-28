package io.github.jonarzz;

public class MyClass {

    public static String something() {
        return "Hello";
    }

    public static String testAble() {
        if (something().equals("Hello")) {
            return "Wow";
        }
        return "LoL";
    }
}
