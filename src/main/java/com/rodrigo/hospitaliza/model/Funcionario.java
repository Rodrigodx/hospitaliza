package com.rodrigo.hospitaliza.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.rodrigo.hospitaliza.enums.FuncaoEnum;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private FuncaoEnum funcao;
	
	
}
