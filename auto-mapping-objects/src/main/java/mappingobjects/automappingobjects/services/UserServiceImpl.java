package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.LoginUserDto;
import mappingobjects.automappingobjects.dtos.UserDto;
import mappingobjects.automappingobjects.dtos.UserRegisterDto;
import mappingobjects.automappingobjects.entities.Role;
import mappingobjects.automappingobjects.entities.User;
import mappingobjects.automappingobjects.repositories.UserRepository;
import mappingobjects.automappingobjects.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private UserDto loggedUser;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

        this.validatorUtil = validatorUtil;
    }


    @Override
    public String registerUser(UserRegisterDto user) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            stringBuilder.append("Password dont mach");
        } else if (this.validatorUtil.isValid(user)) {
            System.out.println();
            User user1 = this.modelMapper.map(user, User.class);
            if (this.userRepository.count() == 0) {
                user1.setRole(Role.ADMIN);
            } else {
                user1.setRole(Role.USER);
            }
            stringBuilder.append(String.format("%s was registered", user.getFullName()));
            this.userRepository.saveAndFlush(user1);
        } else {
            this.validatorUtil.violations(user)
                    .forEach(e -> stringBuilder.append(String.format("%s%n", e.getMessage())));
        }


        return stringBuilder.toString().trim();
    }

    @Override
    public String loginUser(LoginUserDto loginUserDto) {
        StringBuilder builder = new StringBuilder();

        Optional<User> byEmailAndPassword = this.userRepository
                .findAllByEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword());

        if (byEmailAndPassword.isPresent()){
            if (this.loggedUser != null){
                builder.append("User is already logged in");
            }else {
                this.loggedUser = this.modelMapper.map(byEmailAndPassword, UserDto.class);
                builder.append(String.format("Successfully logged in %s", byEmailAndPassword.get().getFullName()));
            }
        }else {
            builder.append("Incorrect username / password");
        }
        return builder.toString();
    }

    @Override
    public String logout() {
        if (this.loggedUser == null) {
            return "Cannot log out. No user was logged in.";
        }else {
            String message =  String.format("User %s successfully logged out", this.loggedUser.getFullName());
            this.loggedUser = null;
            return message;
        }
    }
}
