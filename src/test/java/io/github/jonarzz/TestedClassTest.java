package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import java.util.*;

class TestedClassTest {

    UserRepository userRepository = mock(UserRepository.class);

    TestedClass testedClass = new TestedClass(userRepository);

    @Test
    void userWithGetter() {
        var email = "email@example.com";
        var password = "admin123";
        var user = new User();
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        testedClass.changeUserPassword(email, password);

        assertEquals(password, user.getPassword());
    }

    @Test
    void userWithoutGetter() {
        var email = "email@example.com";
        var password = "admin123";
        var user = spy(new User());
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        testedClass.changeUserPassword(email, password);

        verify(user).setPassword(password);
    }
}