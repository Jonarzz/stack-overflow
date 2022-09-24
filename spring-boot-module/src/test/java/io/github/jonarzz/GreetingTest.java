package io.github.jonarzz;

import static org.awaitility.Awaitility.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit.jupiter.*;

import java.time.*;

@SpringJUnitConfig(value = {
        QuartzConfig.class,
        Greeting.class
})
public class GreetingTest {

    @SpyBean
    Greeting greeting;

    @Test
    public void whenWaitTenSecond_thenScheduledIsCalledAtLeastTenTimes() {
        await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> verify(greeting, atLeast(1)).sayHello());
    }
}
