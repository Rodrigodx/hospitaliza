package com.rodrigo.hospitaliza.repository;

import java.security.Principal;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Funcionario;

@ApplicationScoped
public class FuncionarioRepositoryDAO {

	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@Inject
	private SecurityContext context;
	
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
	
	public Funcionario findByLogin(String login) {
		return em.find(Funcionario.class, login);
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

	public Funcionario getUserLogged() {
		Principal principal = context.getCallerPrincipal();
		if(principal != null) {
			String login = principal.getName();
			return em.find(Funcionario.class, login);
		}
		return null;
	}
}
