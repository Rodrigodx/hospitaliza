package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.service.FuncionarioService;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario = new Funcionario();
	
	@Inject
	private FuncionarioService service;
	
	public void cadastrar() {
		service.save(funcionario);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
