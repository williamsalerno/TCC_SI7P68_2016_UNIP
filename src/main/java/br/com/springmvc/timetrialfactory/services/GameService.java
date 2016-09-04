package br.com.springmvc.timetrialfactory.services;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;

import br.com.springmvc.timetrialfactory.models.Game;

public interface GameService {

	List<Game> listGames();

	void saveGame(Game game);

	void updateGame(Game game) throws PSQLException, ConstraintViolationException;

	void deleteGame(Game game);

	void insertGameInCart(Game game);
	
	Game findGameById(Long id);

}
