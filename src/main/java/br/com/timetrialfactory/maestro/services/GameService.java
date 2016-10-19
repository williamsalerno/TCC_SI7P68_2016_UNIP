package br.com.timetrialfactory.maestro.services;

import java.util.Set;

import br.com.timetrialfactory.maestro.dto.GameDTO;

public interface GameService {

	Set<GameDTO> listGames();

	void saveGame(GameDTO game);

	void updateGame(GameDTO game);

	void deleteGame(GameDTO game);

	GameDTO findGameById(Long id);
	
}
