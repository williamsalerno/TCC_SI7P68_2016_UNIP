package br.com.springmvc.timetrialfactory.daos;

import br.com.springmvc.timetrialfactory.models.User;

public interface UserDAO {

	User findById(Long id);

	void saveUser(User user);

	void updateUser(User user);

	User getUserByLoginAndPassword(String login, String password);

	boolean checkUser(User user);

}
