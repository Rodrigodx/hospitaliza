package com.rodrigo.hospitaliza.repository;

import java.io.Serializable;
import java.util.List;

import com.rodrigo.hospitaliza.model.Paciente;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PacienteRepositoryDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@Transactional
	public Paciente save(Paciente paciente) {
		if(paciente.getId() == null) {	
			em.persist(paciente);
			return paciente;			
		}else {
			return em.merge(paciente);
		}
	}
	
	public List<Paciente> findAll() {
		return em.createQuery("from Paciente", Paciente.class).getResultList();
	}
	
	public Paciente findById(Long id) {
		return em.find(Paciente.class, id);
	}
	
	public Paciente findByCPF(String cpf) {
		String jpql = "From Paciente p where p.cpf = :cpf";
		
		TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
		query.setParameter("cpf", cpf);
		
		List<Paciente> resultados = query.getResultList();
		
		if(resultados.isEmpty()) {
			return null;
		}else {
			return resultados.get(0);
		}
	}
	
	@Transactional
	public Paciente update(Paciente paciente) {
		return em.merge(paciente);
	}
	
	@Transactional
	public void delete(Long id) {
		Paciente paciente = findById(id);
		if(paciente != null) {
			em.remove(paciente);
		}
	}

}
