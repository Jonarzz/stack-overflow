package io.github.jonarzz;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Autowired
    private ObjectProvider<MyObject> myObject;

    public SomeOtherClass getSomeOtherClass() {
        return new SomeOtherClass(myObject.getIfAvailable());
    }

}
