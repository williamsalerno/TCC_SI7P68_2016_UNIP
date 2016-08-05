package br.com.springmvc.timetrialfactory.daos.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.RoleDAO;
import br.com.springmvc.timetrialfactory.models.Role;

@Repository("roleDao")
@Scope("prototype")
public class RoleDAOImpl extends AbstractDAO<Long, Role> implements RoleDAO{

	@Override
	public void saveUserRole(Long idUser, Long idRole) {
		getSession().createQuery("insert into User (roles) select u.id from User u");
	}

}
