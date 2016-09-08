package br.com.timetrialfactory.maestro.services;

import java.util.List;

import br.com.timetrialfactory.maestro.models.Game;

public interface GameService {

	List<Game> listGames();

	void saveGame(Game game);

	void updateGame(Game game);

	void deleteGame(Game game);

	void insertGameInCart(Game game);
	
	Game findGameById(Long id);

}
