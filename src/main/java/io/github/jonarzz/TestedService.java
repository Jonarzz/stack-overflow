package io.github.jonarzz;

import org.springframework.stereotype.Service;

@Service
class TestedService implements TestedServiceInterface {

    private MockedUtilInterface mockedUtil;

    TestedService(MockedUtilInterface mockedUtil) {
        this.mockedUtil = mockedUtil;
    }

    @Override
    public String test() {
        return mockedUtil.value() + " - not a test value";
    }

}
