package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class DynamicPropertiesTest {

    private static final String KEY_A = "key a";
    private static final String KEY_B = "key b";

    @Test
    void withoutEq() {
        DynamicProperties dynamicProperties = mock(DynamicProperties.class);
        when(dynamicProperties.getEnvironmentProperty(KEY_A, Boolean.class))
                .thenReturn(true);
        when(dynamicProperties.getEnvironmentProperty(KEY_B, Long.class))
                .thenReturn(1L);

        assertAll(
                () -> assertTrue(dynamicProperties.getEnvironmentProperty(KEY_A, Boolean.class)),
                () -> assertEquals(1, dynamicProperties.getEnvironmentProperty(KEY_B, Long.class))
        );
    }

    @Test
    void withEq() {
        DynamicProperties dynamicProperties = mock(DynamicProperties.class);
        when(dynamicProperties.getEnvironmentProperty(eq(KEY_A), eq(Boolean.class)))
                .thenReturn(true);
        when(dynamicProperties.getEnvironmentProperty(eq(KEY_B), eq(Long.class)))
                .thenReturn(1L);

        assertAll(
                () -> assertTrue(dynamicProperties.getEnvironmentProperty(KEY_A, Boolean.class)),
                () -> assertEquals(1, dynamicProperties.getEnvironmentProperty(KEY_B, Long.class))
        );
    }

}