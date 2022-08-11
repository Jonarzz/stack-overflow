package io.github.jonarzz;

import static io.github.jonarzz.MockitoStaticVoidTest.StaticClass.ACTUAL_OBJECT;
import static io.github.jonarzz.MockitoStaticVoidTest.StaticClass.ACTUAL_PRIMITIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockitoStaticVoidTest {

    static class StaticClass {

        static final int ACTUAL_PRIMITIVE = -7;
        static final Object ACTUAL_OBJECT = new Object();
        static final RuntimeException ACTUAL_EXCEPTION = new IllegalStateException("Should not be reached");

        static int primitiveTest() {
            return ACTUAL_PRIMITIVE;
        }

        static Object objectTest() {
            return ACTUAL_OBJECT;
        }

        static void voidTest() {
            throw ACTUAL_EXCEPTION;
        }
    }

    @Nested
    class PrimitiveTest {

        @Test
        void answerDefined() {
            try (var mockedStatic = Mockito.mockStatic(StaticClass.class)) {
                var mockedValue = 5;
                mockedStatic.when(StaticClass::primitiveTest)
                            .thenAnswer(inv -> mockedValue);

                var result = StaticClass.primitiveTest();

                assertThat(result)
                        .isEqualTo(mockedValue);
                assertThat(result)
                        .isNotEqualTo(ACTUAL_PRIMITIVE);
            }
        }

        @Test
        void defaultMock() {
            try (var mockedStatic = Mockito.mockStatic(StaticClass.class)) {
                var result = StaticClass.primitiveTest();

                assertThat(result)
                        .isEqualTo(0);
                assertThat(result)
                        .isNotEqualTo(ACTUAL_PRIMITIVE);
            }
        }

        @Test
        void noMock() {
            var result = StaticClass.primitiveTest();

            assertThat(result)
                    .isEqualTo(ACTUAL_PRIMITIVE);
        }

    }

    @Nested
    class ObjectTest {

        @Test
        void answerDefined() {
            try (var mockedStatic = Mockito.mockStatic(StaticClass.class)) {
                var mockedValue = new Object();
                mockedStatic.when(StaticClass::objectTest)
                            .thenAnswer(inv -> mockedValue);

                var result = StaticClass.objectTest();

                assertThat(result)
                        .isEqualTo(mockedValue);
                assertThat(result)
                        .isNotEqualTo(ACTUAL_OBJECT);
            }
        }

        @Test
        void defaultMock() {
            try (var mockedStatic = Mockito.mockStatic(StaticClass.class)) {
                var result = StaticClass.objectTest();

                assertThat(result)
                        .isNull();
            }
        }

        @Test
        void noMock() {
            var result = StaticClass.objectTest();

            assertThat(result)
                    .isEqualTo(ACTUAL_OBJECT);
        }

    }

    @Nested
    class VoidTest {

        @Test
        void answerDefined() {
            try (var mockedStatic = Mockito.mockStatic(StaticClass.class)) {
                mockedStatic.when(StaticClass::voidTest)
                            .thenAnswer(inv -> null);

                assertThatNoException()
                        .isThrownBy(StaticClass::voidTest);
            }
        }

        @Test
        void defaultMock() {
            try (var mockedStatic = Mockito.mockStatic(StaticClass.class)) {
                assertThatNoException()
                        .isThrownBy(StaticClass::voidTest);
            }
        }

        @Test
        void noMock() {
            assertThatThrownBy(StaticClass::voidTest)
                    .isSameAs(StaticClass.ACTUAL_EXCEPTION);
        }

    }

}
