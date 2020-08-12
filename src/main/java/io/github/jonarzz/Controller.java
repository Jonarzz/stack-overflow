package io.github.jonarzz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
class Controller {

    private final IProcessor processor;

    public Controller(ProcessorFactory factory) {
        processor = factory.getInstance();
    }

    @PostMapping("test/test")
    String test(Object input) {
        return processor.process(input);
    }

}