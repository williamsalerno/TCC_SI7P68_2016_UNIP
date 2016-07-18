package br.com.springmvc.timetrialfactory.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6393817383695490874L;
	
	@Id
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}

}
