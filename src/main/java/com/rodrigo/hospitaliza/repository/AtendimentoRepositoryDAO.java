package com.rodrigo.hospitaliza.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	
	
	

}
