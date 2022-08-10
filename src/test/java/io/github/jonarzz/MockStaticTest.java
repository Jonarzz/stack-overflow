package io.github.jonarzz;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class MockStaticTest {

    @Test
    void test() {
        var objectUnderTest = new ClassUnderTest();

        try (MockedStatic<TemplateRendererUtil> mockedStatic = Mockito.mockStatic(TemplateRendererUtil.class)) {
            mockedStatic.when(() -> TemplateRendererUtil.render(anyInt(), anyLong(), anyString(), anyMap()))
                        .thenAnswer(inv -> null);
            objectUnderTest.execute();
        }
    }
}
