package repositories;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Patient;

@ApplicationScoped
public class PatientRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public Patient save (Patient patient) {
		if (patient != null) {
			em.persist(patient);
		} else {
			em.merge(patient);
		}
		
		return patient;
	}
	
	public List<Patient> findAll (){
		return em.createQuery("select * from Usuario", Patient.class).getResultList();
	}
	
	public Patient findById(Integer id) {
		return em.find(Patient.class, id);
	}
	
	public void delete(Integer id) {
		Patient patient = findById(id);
		em.remove(patient);
	}
	
	
}
