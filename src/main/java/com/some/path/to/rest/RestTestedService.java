package com.some.path.to.rest;

import io.github.jonarzz.MockedUtilInterface;
import io.github.jonarzz.TestedServiceInterface;
import org.springframework.stereotype.Service;

@Service
class RestTestedService implements TestedServiceInterface {

    private MockedUtilInterface mockedUtil;

    RestTestedService(MockedUtilInterface mockedUtil) {
        this.mockedUtil = mockedUtil;
    }

    @Override
    public String test() {
        return mockedUtil.value() + " - rest service from component scan value";
    }

}
