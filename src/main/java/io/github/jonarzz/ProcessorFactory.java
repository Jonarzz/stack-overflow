package io.github.jonarzz;

import org.springframework.stereotype.Component;

@Component
class ProcessorFactory {

    private final Dep1 dep1;
    private final Dep2 dep2;

    public ProcessorFactory(Dep1 dep1, Dep2 dep2) {
        this.dep1 = dep1;
        this.dep2 = dep2;
    }

    public IProcessor getInstance() {
        if (2 > 1) {
            return new ProcessorA(dep1, dep2);
        }
        return new ProcessorB(dep1, dep2);
    }

}