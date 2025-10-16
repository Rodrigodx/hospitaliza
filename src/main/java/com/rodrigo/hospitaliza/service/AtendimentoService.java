package com.rodrigo.hospitaliza.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.rodrigo.hospitaliza.enums.PrioridadeEnum;
import com.rodrigo.hospitaliza.enums.StatusEnum;
import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.repository.AtendimentoRepositoryDAO;
import com.rodrigo.hospitaliza.repository.PacienteRepositoryDAO;

@ApplicationScoped
public class AtendimentoService {

	@Inject
	private AtendimentoRepositoryDAO repository;
	
	@Transactional
	public Atendimento save(Atendimento atendimento) {
		
		repository.save(atendimento);
		return atendimento;
	}
	
}
