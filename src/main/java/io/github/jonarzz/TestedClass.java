package io.github.jonarzz;

class TestedClass {

    private UserRepository userRepository;

    TestedClass(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void changeUserPassword(String email, String password) {
        var user = userRepository.findByEmail(email)
                                 .orElseThrow(() -> new RuntimeException("Not found user with email " + email));
        user.setPassword(password);
        userRepository.save(user);
    }
}
