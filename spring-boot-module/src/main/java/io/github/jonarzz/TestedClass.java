package io.github.jonarzz;

import static java.lang.Thread.*;
import static java.util.concurrent.TimeUnit.*;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
class TestedClass {

    private SomeService someService;

    TestedClass(SomeService someService) {
        this.someService = someService;
    }

    void bypassProxy() {
        execute();
    }

    @Async
    void execute() {
        System.out.println(currentThread().getName() + " | @Async start");
        try {
            MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        someService.doStuff();
        System.out.println(currentThread().getName() + " | @Async end");
    }

}
