package softuni.productsshop.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.productsshop.dtos.UserSeenDto;
import softuni.productsshop.entities.Users;
import softuni.productsshop.repositories.UserRepository;
import softuni.productsshop.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/jsons/users.json")));

        UserSeenDto[] userSeenDto = this.gson.fromJson(content, UserSeenDto[].class);
        for (UserSeenDto seenDto : userSeenDto) {
            Users map = this.modelMapper.map(seenDto, Users.class);
            this.userRepository.saveAndFlush(map);
        }
    }
}
