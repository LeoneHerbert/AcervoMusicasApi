package br.com.israel.acervomusicasapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

class GenericoDao<T, J> {

	private final JpaRepository<T, J> repository;
	private Class<T> tClass;

	GenericoDao(JpaRepository<T, J> repository, Class<T> tClass) {
		this.tClass = tClass;
		this.repository = repository;
	}

	T salvar(T entity) {
		return repository.save(entity);
	}

	Optional<T> buscarPor(J id) {
		return repository.findById(id);
	}
	
	List<T> buscarTodos() {
		return repository.findAll();
	}

	void excluirPor(J id) {
		this.repository.deleteById(id);
	}
}