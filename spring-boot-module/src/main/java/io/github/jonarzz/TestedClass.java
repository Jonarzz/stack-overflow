package io.github.jonarzz;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
class TestedClass {

    private SomeService someService;

    TestedClass(SomeService someService) {
        this.someService = someService;
    }

    @Async
    void execute() {
        someService.doStuff();
    }

}
