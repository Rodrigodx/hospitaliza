package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rodrigo.hospitaliza.model.Paciente;
import com.rodrigo.hospitaliza.service.PacienteService;



public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente paciente = new Paciente();
	
	@Inject
	private PacienteService service;
	
	public void salvar() {
		service.save(paciente);
		paciente = new Paciente();
	}
	
	public Paciente getPaciente() {
		return this.paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
