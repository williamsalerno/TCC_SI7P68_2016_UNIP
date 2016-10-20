package br.com.timetrialfactory.maestro.services;

import static br.com.timetrialfactory.maestro.models.enums.RoleType.GENERIC;
import static java.util.UUID.randomUUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.assembler.UserAssembler;
import br.com.timetrialfactory.maestro.daos.UserDAO;
import br.com.timetrialfactory.maestro.dto.UserDTO;
import br.com.timetrialfactory.maestro.models.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Autowired
	private UserAssembler assembler;

	@Override
	public UserDTO findById(Long id) {
		return assembler.toObject(dao.findById(id));
	}

	@Override
	public boolean saveUser(UserDTO dto) {
		if (dto != null) {
			User user = assembler.toEntity(dto);
			if (dao.checkUser(user)) {
				user.setRole(GENERIC);
				user.setActivationCode(randomUUID().getMostSignificantBits());
				user.setActive(false);
				dao.saveUser(user);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public void updateUser(UserDTO user) {
		if (user != null) {
			UserDTO entity = assembler.toObject(dao.findById(user.getId()));
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setAddress(user.getAddress());
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
			if (!entity.isActive()) {
				entity.setActive(user.isActive());
			}
		}
	}

	@Override
	public UserDTO loadUser(String login, String password) {
		if (login != null && password != null) {
			return assembler.toObject(dao.getUserByLoginAndPassword(login, password));
		}
		return new UserDTO();
	}

	@Override
	public UserDTO findByCode(Long code) {
		if (code != null) {
			return assembler.toObject(dao.findByCode(code));
		}
		return new UserDTO();
	}

	@Override
	public UserDTO findByEmailAndUsername(String email, String login) {
		if (email != null && login != null) {
			return assembler.toObject(dao.findByEmailAndUsername(email, login));
		}
		return new UserDTO();
	}

}