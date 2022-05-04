package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.AddGameDto;
import mappingobjects.automappingobjects.entities.Game;
import mappingobjects.automappingobjects.repositories.GameRepository;
import mappingobjects.automappingobjects.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String addGame(AddGameDto addGameDto) {
        StringBuilder builder = new StringBuilder();
        if (this.validatorUtil.isValid(addGameDto)){
            Game game = this.modelMapper.map(addGameDto, Game.class);
            this.gameRepository.saveAndFlush(game);

            builder.append(String.format("Added %s", addGameDto.getTitle()));
        }else {
            this.validatorUtil.violations(addGameDto)
                    .forEach(e -> builder.append(e.getMessage()).append(System.lineSeparator()));
        }
        return builder.toString();
    }
}
