package com.rodrigo.hospitaliza.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.repository.AtendimentoRepositoryDAO;

@ApplicationScoped
public class AtendimentoService {

	@Inject
	private AtendimentoRepositoryDAO repository;

	@Inject
	private GeradorSenhaService geradorSenhaService;

	@Inject
	private FilaTriagemService filaTriagemService;

	@Transactional
	public Atendimento save(Atendimento atendimento) {
		atendimento.setSenhaTriagem(geradorSenhaService.gerarSenhaTriagem());
		filaTriagemService.adicionarAtendimentoFila(atendimento);
		repository.save(atendimento);
		return atendimento;
	}

	public Atendimento findById(Long id) {
		return repository.findById(id);
	}

}
