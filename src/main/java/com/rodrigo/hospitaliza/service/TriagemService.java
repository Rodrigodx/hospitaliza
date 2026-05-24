package com.rodrigo.hospitaliza.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import com.rodrigo.hospitaliza.beans.UsuarioLogadoBean;
import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.model.Triagem;
import com.rodrigo.hospitaliza.repository.TriagemRepositoryDAO;

@ApplicationScoped
public class TriagemService {

	@Inject
	private TriagemRepositoryDAO repository;

	@Inject
	private UsuarioLogadoBean usuarioLogado;

	@Transactional
	public Triagem save(Triagem triagem, Atendimento atendimentoAtual) {

		System.out.println("=== [DEBUG SERVICE] SALVANDO TRIAGEM ===");

		if (usuarioLogado.isLogado()) {
			Funcionario logado = usuarioLogado.getFuncionario();
			System.out.println(">>> DEBUG: Funcionário capturado da sessão manual: " + logado.getNome() + " (ID: "
					+ logado.getId() + ")");

			triagem.setFuncionario(logado);
		} else {
			System.out.println(">>> DEBUG: CRÍTICO! Ninguém logado na sessão ao tentar salvar triagem.");
		}

		triagem.setAtendimento(atendimentoAtual);

		repository.save(triagem);

		return triagem;
	}
}
