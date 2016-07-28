package br.com.springmvc.timetrialfactory.daos.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.models.Game;

@Repository("gameDao")
public class GameDAOImpl extends AbstractDAO<Long, Game> implements GameDAO {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Game> listGames() {
		return getSession().createCriteria(Game.class).list();
	}

	@Override
	public Game findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void saveGame(Game game) {
		persist(game);
	}

	@Override
	public void updateGame(Game game) {
		update(game);
	}

	@Override
	public void deleteGame(Game game) {
		delete(game);
	}

}
