package br.com.springmvc.timetrialfactory.assembler;

import org.springframework.stereotype.Component;

import br.com.springmvc.timetrialfactory.dto.UserDTO;
import br.com.springmvc.timetrialfactory.models.User;

@Component
public class UserAssembler implements Assembler<User, UserDTO> {

	@Override
	public UserDTO toObject(User entity) {
		UserDTO object = null;
		if(entity != null){
			object = new UserDTO();
			object.setId(entity.getId());
			object.setFirstName(entity.getFirstName());
			object.setLastName(entity.getLastName());
			object.setEmail(entity.getEmail());
			object.setAddress(entity.getAddress());
			object.setLogin(entity.getLogin());
			object.setPassword(entity.getPassword());
			object.setRole(entity.getRole());
		}
		return object;
	}

	@Override
	public User toEntity(UserDTO object) {
		User entity = null;
		if(object != null){
			entity = new User();
			entity.setId(object.getId());
			entity.setFirstName(object.getFirstName());
			entity.setLastName(object.getLastName());
			entity.setEmail(object.getEmail());
			entity.setLogin(object.getLogin());
			entity.setPassword(object.getPassword());
			entity.setRole(object.getRole());
		}
		return entity;
	}

}
