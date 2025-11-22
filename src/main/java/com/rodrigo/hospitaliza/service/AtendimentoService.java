package com.rodrigo.hospitaliza.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.rodrigo.hospitaliza.enums.PrioridadeEnum;
import com.rodrigo.hospitaliza.enums.StatusEnum;
import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.repository.AtendimentoRepository;
import com.rodrigo.hospitaliza.repository.AtendimentoRepositoryDAO;
import com.rodrigo.hospitaliza.repository.PacienteRepositoryDAO;

@ApplicationScoped
public class AtendimentoService {

	@Inject
	private AtendimentoRepositoryDAO repositoryDAO;
	
	@Inject
	private AtendimentoRepository atendimentoRepository;
	
	@Inject
	private GeradorSenhaService geradorSenhaService;
	
	@Transactional
	public Atendimento save(Atendimento atendimento) {
		atendimento.setSenhaTriagem(geradorSenhaService.gerarSenhaTriagem());
		atendimentoRepository.save(atendimento);
		return atendimento;
	}
	
	public Atendimento findById(Long id) {
		return atendimentoRepository.findBy(id);
	}
	
}
