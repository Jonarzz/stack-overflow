package io.github.jonarzz;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.*;

@EnableAsync
@SpringBootApplication
public class SpringBootModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootModuleApplication.class, args);
    }

}
