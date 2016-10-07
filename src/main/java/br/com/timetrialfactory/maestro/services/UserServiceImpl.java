package br.com.timetrialfactory.maestro.services;

import static br.com.timetrialfactory.maestro.models.enums.RoleType.GENERIC;
import static java.util.UUID.randomUUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.daos.UserDAO;
import br.com.timetrialfactory.maestro.models.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public boolean saveUser(User user) {
		if (user != null) {
			if (dao.checkUser(user)) {
				user.setRole(GENERIC);
				user.setActivationCode(randomUUID().getMostSignificantBits());
				user.setActive(false);
				dao.saveUser(user);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public void updateUser(User user) {
		if (user != null) {
			User entity = dao.findById(user.getId());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setAddress(user.getAddress());
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
			if (!entity.getActive()) {
				entity.setActive(user.getActive());
			}
		}
	}

	@Override
	public User loadUser(String login, String password) {
		if (login != null && password != null) {
			return dao.getUserByLoginAndPassword(login, password);
		}
		return new User();
	}

	@Override
	public User findByCode(Long code) {
		if (code != null) {
			return dao.findByCode(code);
		}
		return new User();
	}

	@Override
	public User findByEmailAndUsername(String email, String login) {
		if (email != null && login != null) {
			return dao.findByEmailAndUsername(email, login);
		}
		return new User();
	}

}