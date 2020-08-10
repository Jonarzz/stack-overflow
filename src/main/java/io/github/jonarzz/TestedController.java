package io.github.jonarzz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
class TestedController {

    private TestedServiceInterface service;

    TestedController(TestedServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    String test() {
        return service.test();
    }

}
