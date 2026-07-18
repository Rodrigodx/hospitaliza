package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;

import jakarta.enterprise.event.Event;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.rodrigo.hospitaliza.dtos.ChamadaEvent;
import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Triagem;
import com.rodrigo.hospitaliza.service.FilaTriagemService;
import com.rodrigo.hospitaliza.service.PainelManager;
import com.rodrigo.hospitaliza.service.TriagemService;

@Named
@ViewScoped
public class TriagemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Triagem triagem = new Triagem();
	
	private Atendimento atendimentoAtual;
	
	@Inject
	private TriagemService service;
	
	@Inject 
	private FilaTriagemService filaService;
	
	@Inject
	private PainelManager painelManager;
	
	@Inject
	private Event<ChamadaEvent> event; 
	
	public void salvarTriagem() {
		if(atendimentoAtual != null) {			
			service.save(triagem, atendimentoAtual);
			
			this.triagem = new Triagem();
            this.atendimentoAtual = null;
		}
	}
	
	public void chamarProximo() {
		atendimentoAtual = filaService.chamarProximo();
		
		if(atendimentoAtual != null) {
			ChamadaEvent novoChamado = new ChamadaEvent(atendimentoAtual.getSenhaTriagem(),
					atendimentoAtual.getPaciente().getNome(),
					"10");
			
			painelManager.setUltimoChamado(novoChamado);
		}			
	}

	public Triagem getTriagem() {
		return triagem;
	}

	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}

	public Atendimento getAtendimentoAtual() {
		return atendimentoAtual;
	}

	public void setAtendimentoAtual(Atendimento atendimentoAtual) {
		this.atendimentoAtual = atendimentoAtual;
	}

	public Event<ChamadaEvent> getEvent() {
		return event;
	}

	public void setEvent(Event<ChamadaEvent> event) {
		this.event = event;
	}
}
