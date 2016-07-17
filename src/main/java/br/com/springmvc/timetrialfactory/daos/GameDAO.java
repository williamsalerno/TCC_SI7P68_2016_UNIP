package br.com.springmvc.timetrialfactory.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.interfaces.dao.DAO;
import br.com.springmvc.timetrialfactory.models.Game;

@Repository
public class GameDAO implements DAO {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void save(Object obj) {
		manager.persist((Game) obj);

	}

	@Override
	public Object load(Long id) {
		return id;
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	public List<Game> list() {
		return manager.createQuery("select g from Game g", Game.class).getResultList();
	}

}
