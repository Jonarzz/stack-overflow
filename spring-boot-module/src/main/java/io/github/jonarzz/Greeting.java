package io.github.jonarzz;

import org.springframework.stereotype.*;

import java.time.*;

@Component
public class Greeting {

    public void sayHello() {
        System.out.println("Hello at " + LocalDateTime.now());
    }
}
