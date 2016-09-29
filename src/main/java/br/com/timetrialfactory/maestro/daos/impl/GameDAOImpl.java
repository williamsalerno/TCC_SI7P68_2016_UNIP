package br.com.timetrialfactory.maestro.daos.impl;

import static br.com.timetrialfactory.maestro.assembler.GameAssembler.toSet;

import java.util.Set;

import org.springframework.stereotype.Repository;

import br.com.timetrialfactory.maestro.daos.AbstractDAO;
import br.com.timetrialfactory.maestro.daos.GameDAO;
import br.com.timetrialfactory.maestro.models.Game;

@Repository("gameDao")
public class GameDAOImpl extends AbstractDAO<Long, Game> implements GameDAO {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Set<Game> listGames() {
		return toSet(getSession().createCriteria(Game.class).list());
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
