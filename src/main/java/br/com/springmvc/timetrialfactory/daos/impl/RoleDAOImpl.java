package br.com.springmvc.timetrialfactory.daos.impl;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.RoleDAO;
import br.com.springmvc.timetrialfactory.models.Role;

@Repository("roleDao")
public class RoleDAOImpl extends AbstractDAO<Long, Role> implements RoleDAO{

	@Override
	public void saveRole(Role role) {
		persist(role);
	}

	@Override
	public void updateRole(Role role) {
		update(role);
	}

}
