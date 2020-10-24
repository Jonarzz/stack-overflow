package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class ConstructorInjectionTest {

    @Test
    void testWithMock() {
        TargetModelObjectFactory factory = mock(TargetModelObjectFactory.class);
        SourceToTargetMapper mapper = new SourceToTargetMapper(factory);
        Source source = mock(Source.class);
        Target target = mock(Target.class);
        when(factory.create(Target.class))
                .thenReturn(target);

        Target result = mapper.apply(source);

        assertSame(target, result);
    }

    @Test
    void testWithSpy() {
        TargetModelObjectFactory factory = spy(new TargetModelObjectFactory());
        SourceToTargetMapper mapper = new SourceToTargetMapper(factory);
        Source source = mock(Source.class);
        Target target = mock(Target.class);
        when(factory.create(Target.class))
                .thenReturn(target);

        Target result = mapper.apply(source);

        assertSame(target, result);
    }

}
