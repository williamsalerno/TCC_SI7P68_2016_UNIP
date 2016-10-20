package br.com.timetrialfactory.maestro.assembler;

import static br.com.timetrialfactory.maestro.models.enums.RoleType.valueOf;

import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.models.User;

public class UserAssembler implements Assembler<UserDTO, User> {

	@Override
	public UserDTO toObject(User entity) {
		UserDTO object = new UserDTO();
		if (entity != null) {
			object.setId(entity.getId());
			object.setFirstName(entity.getFirstName());
			object.setLastName(entity.getLastName());
			object.setLogin(entity.getLogin());
			object.setPassword(entity.getPassword());
			object.setEmail(entity.getEmail());
			object.setAddress(entity.getAddress());
			if (entity.getRole() != null) {
				object.setRole(entity.getRole().name());
			}
			object.setActive(entity.getActive());
			object.setActivationCode(entity.getActivationCode());
		}
		return object;
	}

	@Override
	public User toEntity(UserDTO object) {
		User entity = new User();
		if (object != null) {
			entity.setId(object.getId());
			entity.setFirstName(object.getFirstName());
			entity.setLastName(object.getLastName());
			entity.setLogin(object.getLogin());
			entity.setPassword(object.getPassword());
			entity.setEmail(object.getEmail());
			entity.setAddress(object.getAddress());
			if (object.getRole() != null) {
				entity.setRole(valueOf(object.getRole()));
			}
			object.setActive(entity.getActive());
			object.setActivationCode(entity.getActivationCode());
		}
		return entity;
	}

}
