package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.LoginUserDto;
import mappingobjects.automappingobjects.dtos.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto user);
    String loginUser(LoginUserDto loginUserDto);

    String logout();
}
