package com.rodrigo.hospitaliza.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import com.rodrigo.hospitaliza.enums.EspecialidadeEnum;
import com.rodrigo.hospitaliza.enums.PrioridadeEnum;
import com.rodrigo.hospitaliza.enums.StatusEnum;
import com.rodrigo.hospitaliza.enums.TipoAtendimentoEnum;

@Entity
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_atendimento")
	private LocalDate dataAtendimento = LocalDate.now();
	
	@Column(name = "hora_chegada")
	private LocalTime horaChegada = LocalTime.now();
	
	@Column(name = "hora_inicio")
	private LocalTime horaInicio; 
	
	@Column(name = "hora_fim")
	private LocalTime horaFim;
	
	@Column(name = "senha_triagem", nullable = false)
	private String senhaTriagem;
	
	@Column(name = "senha_atendimento", nullable = false)
	private String senhaAtendimento;
	
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
	
	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Triagem> triagem = new ArrayList<Triagem>();
	
	
	public Atendimento() {
		
	}

	public Atendimento(Long id, LocalDate dataAtendimento, LocalTime horaChegada, LocalTime horaInicio,
			LocalTime horaFim, String senhaTriagem, String senhaAtendimento, PrioridadeEnum prioridade, StatusEnum status, EspecialidadeEnum especialidade,
			@NotNull String motivoAtendimento, String observacoes, TipoAtendimentoEnum tipo, Paciente paciente) {
		this.id = id;
		this.dataAtendimento = dataAtendimento;
		this.horaChegada = horaChegada;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.senhaTriagem = senhaTriagem;
		this.senhaAtendimento = senhaAtendimento;
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

	public LocalTime getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(LocalTime horaChegada) {
		this.horaChegada = horaChegada;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraDeFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}
	
	public String getSenhaTriagem() {
		return senhaTriagem;
	}
	
	public void setSenhaTriagem(String senhaTriagem) {
		this.senhaTriagem = senhaTriagem;
	}
	
	public String getSenhaAtendimento() {
		return senhaAtendimento;
	}
	
	public void setSenhaAtendimento(String senhaAtendimento) {
		this.senhaAtendimento = senhaAtendimento;
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
