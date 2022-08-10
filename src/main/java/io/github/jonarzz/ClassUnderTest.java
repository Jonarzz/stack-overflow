package io.github.jonarzz;

import java.util.Map;

public class ClassUnderTest {

    void execute() {
        TemplateRendererUtil.render(1, 2, "abc", Map.of());
    }

}
