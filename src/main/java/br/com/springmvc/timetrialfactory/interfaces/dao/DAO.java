package br.com.springmvc.timetrialfactory.interfaces.dao;

public interface DAO {

	void save(Object obj);

	Object load(Long id);

	void update(Object obj);

	void delete(Object obj);

}
