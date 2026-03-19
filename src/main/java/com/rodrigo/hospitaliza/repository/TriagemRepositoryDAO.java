package com.rodrigo.hospitaliza.repository;

import java.io.Serializable;

import com.rodrigo.hospitaliza.model.Triagem;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TriagemRepositoryDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@Transactional
	public Triagem save (Triagem triagem) {
		if(triagem.getId() == null) {
			em.persist(triagem);
		} else {
			em.merge(triagem);
		}
		
		return triagem;
	}
	
	

}
