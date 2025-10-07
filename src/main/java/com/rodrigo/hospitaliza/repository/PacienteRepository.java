package com.rodrigo.hospitaliza.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;

import com.rodrigo.hospitaliza.model.Paciente;

@ApplicationScoped
public class PacienteRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@Transactional
	public Paciente save(Paciente paciente) {
			em.persist(paciente);
			return paciente;
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
	public void update(Long id, Paciente paciente) {
		em.merge(paciente);
	}
	
	@Transactional
	public void delete(Long id) {
		Paciente paciente = findById(id);
		if(paciente != null) {
			em.remove(paciente);
		}
	}

}
