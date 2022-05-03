package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.UserRegisterDto;
import mappingobjects.automappingobjects.entities.Role;
import mappingobjects.automappingobjects.entities.User;
import mappingobjects.automappingobjects.repositories.UserRepository;
import mappingobjects.automappingobjects.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;

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
}
