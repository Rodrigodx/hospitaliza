package com.rodrigo.hospitaliza.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
	
	@PersistenceContext(unitName = "hospitaliza-pu")
	private EntityManager em;
	
	@ApplicationScoped
	@Produces
	public EntityManager getEntityManager() {
		return em;
	}

}
