package io.github.jonarzz;

import static java.lang.Thread.*;

import org.springframework.stereotype.*;

@Service
class SomeService {

    String doStuff() {
        System.out.println(currentThread().getName() + " | doing stuff in some service");
        return "done";
    }

}
