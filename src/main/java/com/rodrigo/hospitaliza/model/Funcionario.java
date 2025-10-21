package com.rodrigo.hospitaliza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rodrigo.hospitaliza.enums.FuncaoEnum;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;
	
	@Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)|(^\\d{11}$)", message = "CPF deve seguir o formato XXX.XXX.XXX-XX ou ser apenas números")
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	@NotNull
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private FuncaoEnum funcao;
	
	@NotNull
	@Column(length = 50, unique = true)
	private String login;
	
	@NotNull
	@Column(length = 255)
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public FuncaoEnum getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoEnum funcao) {
		this.funcao = funcao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
