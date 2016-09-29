package br.com.timetrialfactory.maestro.daos;

import java.util.Set;

import br.com.timetrialfactory.maestro.models.Game;

public interface GameDAO {
	
	Set<Game> listGames();
	
	Game findById(Long id);
	
	void saveGame(Game game);
	
	void updateGame(Game game);
	
	void deleteGame(Game game);

}
