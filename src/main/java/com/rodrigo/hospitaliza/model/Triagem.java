package com.rodrigo.hospitaliza.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;


@Entity
@Table(name = "triagem")
public class Triagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	private Double pressao;
	
	private Double temperatura;
	
	private Double glicemia;
	
	private String queixa;
	
	@Column(name = "historico_rapido")
	private String historicoRapido;
	
	@Column(name = "escala_dor")
	private Integer escalaDor;
	
	private String observacoes;
	
	@OneToOne
	private Funcionario funcionario;
	
	private LocalTime hora = LocalTime.now();
	
	public Triagem () {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Double getPressao() {
		return pressao;
	}

	public void setPressao(Double pressao) {
		this.pressao = pressao;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getGlicemia() {
		return glicemia;
	}

	public void setGlicemia(Double glicemia) {
		this.glicemia = glicemia;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public String getHistoricoRapido() {
		return historicoRapido;
	}

	public void setHistoricoRapido(String historicoRapido) {
		this.historicoRapido = historicoRapido;
	}

	public Integer getEscalaDor() {
		return escalaDor;
	}

	public void setEscalaDor(Integer escalaDor) {
		this.escalaDor = escalaDor;
	}

	public String getObservaçoes() {
		return observacoes;
	}

	public void setObservaçoes(String observaçoes) {
		this.observacoes = observaçoes;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
