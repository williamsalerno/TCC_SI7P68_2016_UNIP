package br.com.timetrialfactory.maestro.services;

import java.util.Set;

import br.com.timetrialfactory.maestro.dto.GameDTO;
import br.com.timetrialfactory.maestro.models.Game;

public interface GameService {

	Set<GameDTO> listGames();

	void saveGame(Game game);

	void updateGame(Game game);

	void deleteGame(Game game);

	GameDTO findGameById(Long id);
	
}
