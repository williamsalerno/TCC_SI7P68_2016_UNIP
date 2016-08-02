package br.com.springmvc.timetrialfactory.daos.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;

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

}
