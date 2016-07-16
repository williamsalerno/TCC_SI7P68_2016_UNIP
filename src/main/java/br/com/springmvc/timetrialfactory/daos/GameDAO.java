package br.com.springmvc.timetrialfactory.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.models.Game;

@Repository
public class GameDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Game game){
		manager.persist(game);
	}

}
