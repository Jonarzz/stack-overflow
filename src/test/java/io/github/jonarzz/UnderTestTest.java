package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

class TestedClassTest {

    @Test
    @Order(1)
    void test_generateToken() {
        var tested = new TestedClass();
        var jwtSecret = "jwtSecret";
        var claims = new DefaultClaims();
        var mockedValue = "mocked value";
        var builder = mock(Jwts.JwtsBuilder.class, RETURNS_DEEP_STUBS);

        try (var mockedStatic = mockStatic(Jwts.class)) {
            // to mock static methods the object returned from the mockStatic method should be used
            mockedStatic.when(Jwts::claims)
                        .thenReturn(claims);
            mockedStatic.when(Jwts::builder)
                        .thenReturn(builder);
            // here we're not mocking static methods, so a standard when method is used
            // thanks to RETURNS_DEEP_STUBS we can mock a chain of method calls
            when(builder.setClaims(claims)
                        // other argument matchers like argThat() can be used here
                        .setIssuedAt(any())
                        .setExpiration(any())
                        .signWith(SignatureAlgorithm.HS512, jwtSecret)
                        .compact())
                    .thenReturn(mockedValue);

            var result = tested.generateToken("value");

            assertEquals(mockedValue, result);
        }
    }

}