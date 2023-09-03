package io.github.jonarzz;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class Caller {

    @Autowired
    private TestedClass asyncUtil;

    public void myMethod1() {
        myMethod2();
    }

    public void myMethod2() {
        asyncUtil.execute();
    }

}
