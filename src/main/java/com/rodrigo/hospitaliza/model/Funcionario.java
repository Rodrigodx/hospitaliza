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
	@Column(name = "cpf", nullable = false, length = 11)
	@NotNull
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private FuncaoEnum funcao;
	
	
}
