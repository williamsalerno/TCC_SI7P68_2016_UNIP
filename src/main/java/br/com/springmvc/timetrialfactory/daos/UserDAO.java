package br.com.springmvc.timetrialfactory.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.interfaces.dao.DAO;
import br.com.springmvc.timetrialfactory.models.User;

@Repository
public class UserDAO implements DAO {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void save(Object obj) {
		manager.persist((User) obj);
	}

	@Override
	public void load(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

}
