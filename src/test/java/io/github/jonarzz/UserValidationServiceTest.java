package io.github.jonarzz;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

@ExtendWith(MockitoExtension.class)
public class UserValidationServiceTest {

    @Mock
    UserService userService;

    @Mock
    UserEntityService userEntityService;

    @InjectMocks
    UserValidationService validationService;

    @Test
    public void validateUser() throws ExecutionException, InterruptedException {
        var userFuture = completedFuture(new User());
        var userEntityFuture = completedFuture(List.of(new UserEntity()));
        doReturn(userFuture)
                .when(userService)
                .getUser(anyString());
        doReturn(userEntityFuture)
                .when(userEntityService)
                .getUserEntities(anyLong());
        var request = UserRequest.builder()
                                 .userName("admin")
                                 .userEntities(List.of("US", "ASIA", "EUROPE"))
                                 .build();

        boolean result = validationService.validateUserCounterparty(request);

        assertTrue(result);
    }

}
