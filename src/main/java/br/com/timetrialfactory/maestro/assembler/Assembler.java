package br.com.timetrialfactory.maestro.assembler;

public interface Assembler<O, E> {

	O toObject(E entity);

	E toEntity(O object);

}
