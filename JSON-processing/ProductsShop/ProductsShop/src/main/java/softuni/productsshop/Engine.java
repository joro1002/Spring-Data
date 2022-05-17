package softuni.productsshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.productsshop.services.UserService;

@Component
public class Engine implements CommandLineRunner {
    private final UserService userService;

    public Engine(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
    }
}
