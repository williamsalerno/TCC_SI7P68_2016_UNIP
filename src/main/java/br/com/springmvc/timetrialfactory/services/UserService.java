package br.com.springmvc.timetrialfactory.services;

import br.com.springmvc.timetrialfactory.models.User;

public interface UserService {

	User findById(Long id);

	boolean saveUser(User user);

	void updateUser(User user);

	User loadUser(String login, String password);

}
