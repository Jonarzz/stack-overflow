package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FieldInjectionTest {

    @Mock
    TargetModelObjectFactory factory;
    @InjectMocks
    private SourceToTargetMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testWithMock() {
        Source source = mock(Source.class);
        Target target = mock(Target.class);
        when(factory.create(Target.class))
                .thenReturn(target);

        Target result = mapper.apply(source);

        assertSame(target, result);
    }

    @Test
    void testWithSpy() {
        Source source = mock(Source.class);
        Target target = mock(Target.class);
        when(factory.create(Target.class))
                .thenReturn(target);

        Target result = mapper.apply(source);

        assertSame(target, result);
    }

}
