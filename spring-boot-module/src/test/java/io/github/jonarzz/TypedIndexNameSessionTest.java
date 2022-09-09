package io.github.jonarzz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSession;
import org.springframework.session.Session;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public abstract class TypedIndexNameSessionTest<T extends Session> {

    private static final String VALID_SUB = "principal name";

    @MockBean
    private FindByIndexNameSessionRepository<T> sessionRepository;

    abstract T createSession();

    @Test
    void emptySessions() {
        Map<String, T> sessions = new HashMap<>();
        when(sessionRepository.findByPrincipalName(VALID_SUB))
                .thenReturn(sessions);

        var result = sessionRepository.findByPrincipalName(VALID_SUB);

        assertEquals(sessions, result);
    }

    @Test
    void singleSession() {
        Map<String, T> sessions = new HashMap<>();
        sessions.put("test", createSession());
        when(sessionRepository.findByPrincipalName(VALID_SUB))
                .thenReturn(sessions);

        var result = sessionRepository.findByPrincipalName(VALID_SUB);

        assertEquals(sessions, result);
    }

    static class ExampleTest extends TypedIndexNameSessionTest<MapSession> {

        @Override
        MapSession createSession() {
            return new MapSession();
        }
    }
}
