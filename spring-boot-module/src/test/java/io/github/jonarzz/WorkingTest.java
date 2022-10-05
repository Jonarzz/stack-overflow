package io.github.jonarzz;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
public class WorkingTest {

    @Autowired
    MyRepository repository;

    @Test
    void test() {
        // we're wrapping the repository to avoid
        // Mockito interpreting the mocked method as abstract
        // and then we're spying on it to make it easy to modify its behaviour
        var repositorySpy = spy(new MyRepositoryFake(repository));
        var id = 1L;
        doThrow(new RuntimeException("first"))
                .doCallRealMethod()
                .doThrow(new RuntimeException("second"))
                .when(repositorySpy)
                .findById(id);

        assertThatThrownBy(() -> repositorySpy.findById(id))
                .hasMessage("first");
        assertThat(repositorySpy.findById(id))
                .isEmpty();
        assertThatThrownBy(() -> repositorySpy.findById(id))
                .hasMessage("second");
    }
}
