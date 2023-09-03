package io.github.jonarzz;

import static java.lang.Thread.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.*;

@EnableAsync
@SpringBootApplication
public class SpringBootModuleApplication implements ApplicationListener<ContextRefreshedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootModuleApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var testedClass = event.getApplicationContext()
                               .getBean(TestedClass.class);
        invokeTestedClassMethods(testedClass);

        var caller = event.getApplicationContext()
                          .getBean(Caller.class);
        invokeCallerMethods(caller);
    }

    private void invokeTestedClassMethods(TestedClass testedClass) {
        System.out.println(currentThread().getName() + " | bypassProxy - start");
        testedClass.bypassProxy();
        System.out.println(currentThread().getName() + " | bypassProxy - end");
        System.out.println(currentThread().getName() + " | execute - start");
        testedClass.execute();
        System.out.println(currentThread().getName() + " | execute - end");
    }

    private static void invokeCallerMethods(Caller caller) {
        System.out.println(currentThread().getName() + " | myMethod1 - start");
        caller.myMethod1();
        System.out.println(currentThread().getName() + " | myMethod1 - end");
        System.out.println(currentThread().getName() + " | myMethod2 - start");
        caller.myMethod2();
        System.out.println(currentThread().getName() + " | myMethod2 - end");
    }
}
