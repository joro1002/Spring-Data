package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.UserRegisterDto;
import mappingobjects.automappingobjects.entities.Role;
import mappingobjects.automappingobjects.entities.User;
import mappingobjects.automappingobjects.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public String registerUser(UserRegisterDto user) {
        User user1 = this.modelMapper.map(user, User.class);
       if (this.userRepository.count() == 0){
           user1.setRole(Role.ADMIN);
       }else {
           user1.setRole(Role.USER);
       }
       this.userRepository.saveAndFlush(user1);
        return "created";
    }
}
