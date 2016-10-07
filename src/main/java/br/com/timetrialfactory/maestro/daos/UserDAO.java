package br.com.timetrialfactory.maestro.daos;

import br.com.timetrialfactory.maestro.models.User;

public interface UserDAO {

	User findById(Long id);

	void saveUser(User user);

	void updateUser(User user);

	User getUserByLoginAndPassword(String login, String password);

	boolean checkUser(User user);

	User findByCode(Long code);

	User findByEmailAndUsername(String email, String Login);

}
