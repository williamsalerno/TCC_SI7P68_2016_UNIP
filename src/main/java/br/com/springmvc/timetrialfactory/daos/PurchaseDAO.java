package br.com.springmvc.timetrialfactory.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.interfaces.dao.DAO;
import br.com.springmvc.timetrialfactory.models.Purchase;

@Repository
public class PurchaseDAO implements DAO {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void save(Object obj) {
		manager.persist((Purchase) obj);

	}

	@Override
	public Object load(Long id) {
		return manager.createQuery("select p from Purchase p", Purchase.class).equals(id);
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
