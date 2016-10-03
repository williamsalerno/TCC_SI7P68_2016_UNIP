package br.com.timetrialfactory.maestro.services;

import br.com.timetrialfactory.maestro.models.User;

public interface UserService {

	User findById(Long id);

	boolean saveUser(User user);

	void updateUser(User user);

	User loadUser(String login, String password);

	User findByCode(Long code);

	User findByEmail(String email);

}
