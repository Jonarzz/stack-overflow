package io.github.jonarzz;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class UserValidationService {

    private UserService userService;
    private UserEntityService userEntityService;

    public boolean validateUserCounterparty(UserRequest request)
            throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> result = userService.getUser(request.getUserName())
                                                       .thenCompose(user -> userEntityService.getUserEntities(user.getUserId()))
                                                       .thenCompose(userEntities -> validate(userEntities, request.getUserEntities()));

        Boolean validationStatus = result.get();
        if (!validationStatus) {
            System.err.println("Validation failed for user name " + request.getUserName());
        }
        return validationStatus;
    }

    private CompletionStage<Boolean> validate(List<UserEntity> userEntities, List<String> requestEntities) {
        return CompletableFuture.completedFuture(true);
    }

}
