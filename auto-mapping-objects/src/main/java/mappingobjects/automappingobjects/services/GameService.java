package mappingobjects.automappingobjects.services;

import mappingobjects.automappingobjects.dtos.AddGameDto;
import mappingobjects.automappingobjects.dtos.DeleteGameDto;

public interface GameService {
    String addGame(AddGameDto addGameDto);

    String deleteGame(DeleteGameDto deleteGameDto);
}
