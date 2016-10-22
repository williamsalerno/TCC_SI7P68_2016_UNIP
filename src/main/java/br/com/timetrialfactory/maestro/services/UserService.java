package br.com.timetrialfactory.maestro.services;

import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.models.User;

public interface UserService {

	UserDTO findById(Long id);

	boolean saveUser(UserDTO user);

	void updateUser(User user);

	UserDTO loadUser(String login, String password);

	UserDTO findByCode(Long code);

	UserDTO findByEmailAndUsername(String email, String login);

}
