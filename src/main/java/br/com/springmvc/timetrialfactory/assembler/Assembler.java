package br.com.springmvc.timetrialfactory.assembler;

public interface Assembler<T, K> {
	
	K toObject(T entity);
	
	T toEntity(K object);

}
