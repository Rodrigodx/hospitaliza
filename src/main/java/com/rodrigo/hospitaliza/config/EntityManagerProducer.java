package com.rodrigo.hospitaliza.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EntityManagerProducer {
	
	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@ApplicationScoped
	@Produces
	public EntityManager getEntityManager() {
		return em;
	}

}
