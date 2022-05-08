package mappingobjects.automappingobjects;

import mappingobjects.automappingobjects.dtos.AddGameDto;
import mappingobjects.automappingobjects.dtos.DeleteGameDto;
import mappingobjects.automappingobjects.dtos.LoginUserDto;
import mappingobjects.automappingobjects.dtos.UserRegisterDto;
import mappingobjects.automappingobjects.repositories.UserRepository;
import mappingobjects.automappingobjects.services.GameService;
import mappingobjects.automappingobjects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public Runner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] tokens = scanner.nextLine().split("\\|");

            switch (tokens[0]) {
                case "RegisterUser":
                    UserRegisterDto user = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);

                    System.out.println(this.userService.registerUser(user));

                    break;
                case "LoginUser":
                    LoginUserDto loginUser = new LoginUserDto(tokens[1], tokens[2]);
                    System.out.println(this.userService.loginUser(loginUser));
                    break;

                case "Logout":
                    System.out.println(this.userService.logout());
                    break;

                case "AddGame":
                    LocalDate date = LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    AddGameDto game = new AddGameDto(tokens[1], new BigDecimal(tokens[2]), Double.parseDouble(tokens[3]),
                            tokens[4], tokens[5], tokens[6], date);
                    System.out.println(this.gameService.addGame(game));
                    break;
                case "DeleteGame":
                    DeleteGameDto deleteGameDto = new DeleteGameDto(Long.parseLong(tokens[1]));
                    System.out.println(this.gameService.deleteGame(deleteGameDto));
                    break;
            }
        }
    }
}
