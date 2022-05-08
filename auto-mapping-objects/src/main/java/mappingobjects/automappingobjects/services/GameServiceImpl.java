package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.AddGameDto;
import mappingobjects.automappingobjects.dtos.DeleteGameDto;
import mappingobjects.automappingobjects.entities.Game;
import mappingobjects.automappingobjects.repositories.GameRepository;
import mappingobjects.automappingobjects.utils.ValidatorUtil;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public String deleteGame(@NotNull DeleteGameDto deleteGameDto) {
        StringBuilder builder = new StringBuilder();

        Optional<Game> game = this.gameRepository.findById(deleteGameDto.getId());

        if (game.isPresent()){
            this.gameRepository.delete(game.get());
            builder.append(String.format("Game %s was delete.", game.get().getTitle()));
        }else {
            builder.append("Cannot find game");
        }
        return  builder.toString();

    }
}
