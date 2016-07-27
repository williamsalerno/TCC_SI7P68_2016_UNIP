package br.com.springmvc.timetrialfactory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.springmvc.timetrialfactory.daos.UserDAO;
import br.com.springmvc.timetrialfactory.models.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO dao;

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public boolean saveUser(User user) {
		if (dao.checkUser(user)) {
			dao.saveUser(user);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setAddress(user.getAddress());
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = dao.getUserByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("");
		}
		return user;
	}

	@Override
	public User load(String login) {
		return (User) loadUserByUsername(login);
	}

}