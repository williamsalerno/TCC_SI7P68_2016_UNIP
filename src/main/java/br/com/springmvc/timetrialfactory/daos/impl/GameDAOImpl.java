package br.com.springmvc.timetrialfactory.daos.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.models.Game;

@Repository("gameDao")
public class GameDAOImpl extends AbstractDAO<Long, Game> implements GameDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> listGames() {
		Criteria criteria = createEntityCriteria();
		criteria.createCriteria("select * from game").list();
		return (List<Game>) criteria;
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
