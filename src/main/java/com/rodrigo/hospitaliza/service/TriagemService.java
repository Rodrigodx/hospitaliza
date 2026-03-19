package com.rodrigo.hospitaliza.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Triagem;
import com.rodrigo.hospitaliza.repository.TriagemRepositoryDAO;

@ApplicationScoped
public class TriagemService {
	
	@Inject
	private TriagemRepositoryDAO repository;
	
	@Inject
	private AtendimentoService atendimentoService;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private FilaTriagemService filaService;
	
	@Transactional
	public Triagem save(Triagem triagem) {
		Atendimento atendimento = new Atendimento();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		triagem.setFuncionario(funcionarioService.findById(Long.valueOf(session.getId())));
		triagem.setAtendimento(atendimentoService.findById(atendimento.getId()));
		
		repository.save(triagem);
		
		return triagem;
	}
	
}
