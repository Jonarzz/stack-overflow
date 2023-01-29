package io.github.jonarzz;

import org.slf4j.*;

class SomeClass {

    String getFromMdc() {
        return MDC.get("correlation-id");
    }

}
