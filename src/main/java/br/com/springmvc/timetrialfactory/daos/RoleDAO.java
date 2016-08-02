package br.com.springmvc.timetrialfactory.daos;

import br.com.springmvc.timetrialfactory.models.Role;

public interface RoleDAO {
	
	void saveRole(Role role);
	
	void updateRole(Role role);

}
