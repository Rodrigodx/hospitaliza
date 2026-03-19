package com.rodrigo.hospitaliza.repository;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Atendimento;

@ApplicationScoped
public class AtendimentoRepositoryDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@Transactional
	public Atendimento save(Atendimento atendimento) {
		if(atendimento.getId() == null) {
			em.persist(atendimento);
		}else {
			em.merge(atendimento);
		}
		return atendimento;
	}
	
	public List<Atendimento> findAll(){
		return em.createQuery("From Atendimento", Atendimento.class).getResultList();
	}
	
	public Atendimento findById(Long id) {
		return em.find(Atendimento.class, id);
	}
	
	
	

}
