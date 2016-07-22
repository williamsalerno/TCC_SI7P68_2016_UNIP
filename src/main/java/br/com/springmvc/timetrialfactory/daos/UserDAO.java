package br.com.springmvc.timetrialfactory.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.interfaces.dao.DAO;
import br.com.springmvc.timetrialfactory.models.User;

@Repository
public class UserDAO implements DAO, UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void save(Object obj) {
		manager.persist((User) obj);
	}

	@Override
	public Object load(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String jpql = "select u from User u where u.login = :login";
		List<User> users = manager.createQuery(jpql, User.class).setParameter("login", username).getResultList();
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("Login e/ou senha inválidos.");
		}
		return users.get(0);
	}

}
