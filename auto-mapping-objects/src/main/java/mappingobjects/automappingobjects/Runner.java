package mappingobjects.automappingobjects;

import mappingobjects.automappingobjects.dtos.UserRegisterDto;
import mappingobjects.automappingobjects.repositories.UserRepository;
import mappingobjects.automappingobjects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public Runner(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\|");

        switch (tokens[0]){
            case "RegisterUser":
                UserRegisterDto user = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);

                this.userService.registerUser(user);

                break;
        }
    }
}
