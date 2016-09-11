package br.com.timetrialfactory.maestro.assembler;

import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.models.User;

@Component
public class UserAssembler implements Assembler<User, UserDTO> {

	@Override
	public UserDTO toObject(User entity) {
		UserDTO object = null;
		if (entity != null) {
			object = new UserDTO();
			object.setId(entity.getId());
			object.setFirstName(entity.getFirstName());
			object.setLastName(entity.getLastName());
			object.setEmail(entity.getEmail());
			object.setAddress(entity.getAddress());
			object.setLogin(entity.getLogin());
			object.setPassword(entity.getPassword());
			object.setRole(entity.getRole());
			object.setActivationCode(entity.getActivationCode());
			object.setActive(entity.getActive());
		}
		return object;
	}

	@Override
	public User toEntity(UserDTO object) {
		User entity = null;
		if (object != null) {
			entity = new User();
			entity.setId(object.getId());
			entity.setFirstName(object.getFirstName());
			entity.setLastName(object.getLastName());
			entity.setEmail(object.getEmail());
			entity.setAddress(object.getAddress());
			entity.setLogin(object.getLogin());
			entity.setPassword(object.getPassword());
			entity.setRole(object.getRole());
			entity.setActivationCode(object.getActivationCode());
			entity.setActive(object.getActive());
		}
		return entity;
	}

}
