package br.com.springmvc.timetrialfactory.interfaces.dao;

public interface DAO {
	
	void save(Object obj);
	
	void load(Object obj);
	
	void update(Object obj);
	
	void delete(Object obj);

}
