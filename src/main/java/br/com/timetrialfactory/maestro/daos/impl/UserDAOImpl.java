package br.com.timetrialfactory.maestro.daos.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.timetrialfactory.maestro.daos.AbstractDAO;
import br.com.timetrialfactory.maestro.daos.UserDAO;
import br.com.timetrialfactory.maestro.models.User;

@Repository("userDao")
@Scope("prototype")
public class UserDAOImpl extends AbstractDAO<Long, User> implements UserDAO {

	@Override
	public User findById(Long id) {
		return getByKey(id);
	}

	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@Override
	public void updateUser(User user) {
		update(user);
	}

	@Override
	public boolean checkUser(User user) {
		Criteria criteriaLogin = createEntityCriteria();
		Criteria criteriaEmail = createEntityCriteria();
		criteriaLogin.add(Restrictions.eq("login", user.getLogin()));
		criteriaEmail.add(Restrictions.eq("email", user.getEmail()));
		return criteriaLogin.uniqueResult() == null && criteriaEmail.uniqueResult() == null;
	}

	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login)).add(Restrictions.eq("password", password));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public User findByCode(Long code) {
		return (User) getSession().createQuery("FROM User u WHERE u.activationCode = :code").setParameter("code", code)
				.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public User findByEmailAndUsername(String email, String login) {
		return (User) getSession().createQuery("FROM User u WHERE u.email = :email and u.login = :login")
				.setParameter("email", email).setParameter("login", login).uniqueResult();
	}

}
