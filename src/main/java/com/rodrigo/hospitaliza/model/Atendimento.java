package com.rodrigo.hospitaliza.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.rodrigo.hospitaliza.enums.EspecialidadeEnum;
import com.rodrigo.hospitaliza.enums.PrioridadeEnum;
import com.rodrigo.hospitaliza.enums.StatusEnum;
import com.rodrigo.hospitaliza.enums.TipoAtendimentoEnum;

@Entity
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataAtendimento = LocalDate.now();
	
	private LocalTime horaDeChegada = LocalTime.now(); 
	
	private LocalTime horaDeInicio; 
	
	private LocalTime horaDeFim;
	
	@Enumerated(EnumType.ORDINAL)
	private PrioridadeEnum prioridade;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@Enumerated(EnumType.STRING)
	private EspecialidadeEnum especialidade;
	
	@Column(name = "observacoes", length = 255)
	private String observacoes;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoAtendimentoEnum tipo;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	
	public Atendimento() {
		
	}

	public Atendimento(Long id, LocalDate dataAtendimento, LocalTime horaDeChegada, LocalTime horaDeInicio,
			LocalTime horaDeFim, PrioridadeEnum prioridade, StatusEnum status, EspecialidadeEnum especialidade,
			@NotNull String motivoAtendimento, String observacoes, TipoAtendimentoEnum tipo, Paciente paciente) {
		this.id = id;
		this.dataAtendimento = dataAtendimento;
		this.horaDeChegada = horaDeChegada;
		this.horaDeInicio = horaDeInicio;
		this.horaDeFim = horaDeFim;
		this.prioridade = prioridade;
		this.status = status;
		this.especialidade = especialidade;
		this.observacoes = observacoes;
		this.tipo = tipo;
		this.paciente = paciente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public LocalTime getHoraDeChegada() {
		return horaDeChegada;
	}

	public void setHoraDeChegada(LocalTime horaDeChegada) {
		this.horaDeChegada = horaDeChegada;
	}

	public LocalTime getHoraDeInicio() {
		return horaDeInicio;
	}

	public void setHoraDeInicio(LocalTime horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}

	public LocalTime getHoraDeFim() {
		return horaDeFim;
	}

	public void setHoraDeFim(LocalTime horaDeFim) {
		this.horaDeFim = horaDeFim;
	}

	public PrioridadeEnum getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeEnum prioridade) {
		this.prioridade = prioridade;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public EspecialidadeEnum getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeEnum especialidade) {
		this.especialidade = especialidade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public TipoAtendimentoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtendimentoEnum tipo) {
		this.tipo = tipo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
