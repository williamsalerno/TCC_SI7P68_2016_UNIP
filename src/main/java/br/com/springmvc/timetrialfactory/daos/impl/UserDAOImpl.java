package br.com.springmvc.timetrialfactory.daos.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;

@Repository("userDao")
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
	public boolean checkUser(User user) {
		Criteria criteriaLogin = createEntityCriteria();
		Criteria criteriaEmail = createEntityCriteria();
		criteriaLogin.add(Restrictions.eq("login", user.getLogin()));
		criteriaEmail.add(Restrictions.eq("email", user.getEmail()));
		return criteriaLogin.uniqueResult() == null && criteriaEmail.uniqueResult() == null;
	}

	@Override
	public User getUserByLogin(String login) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		return (User) criteria.uniqueResult();
	}

}
