package com.rodrigo.hospitaliza.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.repository.PacienteRepository;

@ApplicationScoped
public class PacienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteRepository repository;
	
	@Transactional
	public Paciente save(Paciente paciente) {
		
		String cpfComMascara = paciente.getCpf();
		
		if(cpfComMascara != null) {
			String cpfFormatado = cpfComMascara.replaceAll("[^0-9]", "");
			
			paciente.setCpf(cpfFormatado);
		}
		
		return repository.save(paciente);
	}
	
	public List<Paciente> findAll() {
		return repository.findAll();
	}
	
	public Paciente findById(Long id) {
		return repository.findById(id);
	}
	
	public Paciente findByCPF(String cpf) {
		return repository.findByCPF(cpf);
	}
	
	@Transactional
	public Paciente update(Paciente paciente) {
		
		if(paciente.getId() == null || repository.findById(paciente.getId()) == null) {
			throw new IllegalArgumentException("Paciente não encontrado.");
		}
		
		return repository.update(paciente);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}
}
