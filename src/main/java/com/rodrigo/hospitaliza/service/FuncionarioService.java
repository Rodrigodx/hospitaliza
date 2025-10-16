package com.rodrigo.hospitaliza.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.repository.FuncionarioRepositoryDAO;

@ApplicationScoped
public class FuncionarioService {

	@Inject
	private FuncionarioRepositoryDAO repository;
	
	@Transactional
	public Funcionario save(Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	public List<Funcionario> findAll(){
		return repository.findAll();
	}
	
	public Funcionario findById(Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public Funcionario update(Funcionario funcionario) {
		return repository.update(funcionario);
	}
	
	@Transactional
	public void delete (Long id) {
		repository.delete(id);
	}
}
