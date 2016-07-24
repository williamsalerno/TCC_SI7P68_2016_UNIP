package br.com.springmvc.timetrialfactory.daos;

import br.com.springmvc.timetrialfactory.models.User;

public interface UserDAO {
	
	User findById(Long id);
	
	void saveUser(User user);
	
	User getUserByLogin(String login);
	
	boolean checkUser(User user);
	
}
