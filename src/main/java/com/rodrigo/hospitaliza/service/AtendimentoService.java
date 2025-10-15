package com.rodrigo.hospitaliza.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.rodrigo.hospitaliza.enums.PrioridadeEnum;
import com.rodrigo.hospitaliza.enums.StatusEnum;
import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.repository.AtendimentoRepository;
import com.rodrigo.hospitaliza.repository.PacienteRepository;

@ApplicationScoped
public class AtendimentoService {

	@Inject
	private AtendimentoRepository repository;
	
	@Transactional
	public Atendimento save(Atendimento atendimento) {
		
		atendimento.setStatus(StatusEnum.AGUARDANDO_TRIAGEM);
		atendimento.setPrioridade(PrioridadeEnum.AMARELO);
		
		repository.save(atendimento);
		return atendimento;
	}
	
}
