package br.com.timetrialfactory.maestro.assembler;

public interface Assembler<T, K> {
	
	K toObject(T entity);
	
	T toEntity(K object);

}
