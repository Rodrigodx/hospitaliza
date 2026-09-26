package services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import models.Patient;
import repositories.PatientRepository;

@ApplicationScoped
public class PatientService {

	@Inject
	private PatientRepository repository;
	
	@Transactional
	public void save(Patient patient) {
		repository.save(patient);
	}
	
	public Patient findById(Integer id) {
		return repository.findById(id);
	}
	
	public List<Patient> findAll(){
		return repository.findAll();
	}
	
	public void delete (Integer id) {
		repository.delete(id);
	}
}
