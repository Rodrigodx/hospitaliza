package com.rodrigo.hospitaliza.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Funcionario;

@ApplicationScoped
public class FuncionarioRepositoryDAO {

	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@Transactional
	public Funcionario save(Funcionario funcionario) {
		em.persist(funcionario);
		return funcionario;
	}
	
	public List<Funcionario> findAll(){
		return em.createQuery("From Funcionario", Funcionario.class).getResultList();
	}
	
	public Funcionario findById(Long id) {
		return em.find(Funcionario.class, id);
	}
	
	@Transactional
	public Funcionario update(Funcionario funcionario) {
		return em.merge(funcionario);
	}
	
	@Transactional
	public void delete(Long id) {
		Funcionario funcionario = findById(id);
		em.remove(funcionario);
	}
}
