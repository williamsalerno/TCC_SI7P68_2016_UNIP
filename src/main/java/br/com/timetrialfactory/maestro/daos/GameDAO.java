package br.com.timetrialfactory.maestro.daos;

import java.util.List;

import br.com.timetrialfactory.maestro.models.Game;

public interface GameDAO {
	
	List<Game> listGames();
	
	Game findById(Long id);
	
	void saveGame(Game game);
	
	void updateGame(Game game);
	
	void deleteGame(Game game);

}
