package io.github.jonarzz;

public class SomeService {

    public String doSth(String id) {
        SomeConfig config = UtilClass.getConfigForId(id);
        return config.getValue();
    }

}
