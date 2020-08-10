package io.github.jonarzz;

import org.springframework.stereotype.Component;

@Component
public class DefaultMockedUtil implements MockedUtilInterface {

    @Override
    public int value() {
        return 0;
    }

}
