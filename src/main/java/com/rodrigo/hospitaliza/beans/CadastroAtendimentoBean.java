package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rodrigo.hospitaliza.model.Atendimento;
import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.service.AtendimentoService;
import com.rodrigo.hospitaliza.service.PacienteService;

@Named
@ViewScoped
public class CadastroAtendimentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Paciente paciente = new Paciente();
	
	private Atendimento atendimento = new Atendimento();

	@Inject
	private AtendimentoService atendimentoService;
	
	@Inject
	private PacienteService pacienteService;

	public void iniciarAtendimento() {
		
		pacienteService.save(paciente);
		
		atendimentoService.save(atendimento);
	}
	
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
