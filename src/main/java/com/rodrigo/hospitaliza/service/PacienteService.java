package com.rodrigo.hospitaliza.service;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.repository.PacienteRepositoryDAO;

@ApplicationScoped
public class PacienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject 
	private PacienteRepositoryDAO pacienteRepository;
	
	@Inject
	private ConverterDateService convertDate;

	@Transactional
	public Paciente save(Paciente paciente) {

		String cpfComMascara = paciente.getCpf();

		if (cpfComMascara != null) {
			String cpfFormatado = cpfComMascara.replaceAll("[^0-9]", "");

			paciente.setCpf(cpfFormatado);
		}
		
		return pacienteRepository.save(paciente);
	}

	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}

	public Paciente findById(Long id) {
		return pacienteRepository.findById(id);
	}

	public Paciente findByCPF(String cpf) {
		return pacienteRepository.findByCPF(cpf);
	}

	@Transactional
	public Paciente update(Paciente paciente) {
		Paciente existente = findById(paciente.getId());

		if (existente == null) {
			throw new IllegalArgumentException("Paciente não encontrado!");
		}

		existente.setNome(paciente.getNome());
		existente.setCpf(paciente.getCpf());
		existente.setDataNascimento(paciente.getDataNascimento());
		existente.setEndereco(paciente.getEndereco());
		existente.setTelefone(paciente.getTelefone());
		existente.setEmail(paciente.getEmail());

		return pacienteRepository.save(existente);
	}

	@Transactional
	public void delete(Long id) {
		pacienteRepository.delete(id);
	}
}
