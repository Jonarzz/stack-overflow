package io.github.jonarzz;

import static org.awaitility.Awaitility.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.junit.jupiter.*;

import java.time.*;

@SpringJUnitConfig(value = {
        QuartzConfig.class,
        GreetingTest.Config.class
})
public class GreetingTest {

    @Autowired
    Greeting greeting;

    @Test
    public void whenWaitTenSecond_thenScheduledIsCalledAtLeastTenTimes() {
        await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> verify(greeting, atLeast(1)).sayHello());
    }

static class Config {

    @Bean
    @Primary
    Greeting greeting() {
        return spy(new Greeting());
    }
}
}
