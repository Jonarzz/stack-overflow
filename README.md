# Question

https://stackoverflow.com/questions/63438057/using-mokito-when-for-method-with-string-and-classt-arguments

# Answer

The problem is caused by mixing mocking with and without argument matchers. If you use an argument matcher to one of the mocked method arguments, you have to use a matcher for all of them. You can read more [here][1].

I've created a simple [project on GitHub][2] with a solution - you can check it if you like, but here's the snippet:

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

As you can see - one of the methods uses `eq` matcher for both of the method's arguments and the other one uses none. Both tests pass.

In your case 

    when(dynamicProperties.getEnvironmentProperty(eq(KEY_A), anyObject())).thenReturn(true);

this mock did not cause problems, because `eq` and `anyObject` are both argument matchers, so there was no error, but 

    when(dynamicProperties.getEnvironmentProperty(eq(KEY_A), Boolean.class)).thenReturn(true);

has a matcher (`eq`) and a simple object (`Boolean.class` without `eq`).

  [1]: https://javadoc.io/static/org.mockito/mockito-core/3.3.3/org/mockito/ArgumentMatchers.html
  [2]: https://github.com/Jonarzz/stack-overflow/tree/63438057