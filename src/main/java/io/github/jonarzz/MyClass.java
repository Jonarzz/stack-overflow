package io.github.jonarzz;

import java.util.*;

public class MyClass {

    private static boolean MY_STATIC_BOOL = getYourStaticBool();

    public String doSomething() {
        if (MY_STATIC_BOOL) {
            return "Success";
        }
        return "Failure";
    }

    protected static boolean getYourStaticBool() {
        return Optional.ofNullable(System.getenv("foo"))
                       .map(Boolean::parseBoolean)
                       .orElse(false);
    }
}
