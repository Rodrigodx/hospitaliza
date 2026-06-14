package com.rodrigo.hospitaliza.service;

import java.io.Serializable;

import com.rodrigo.hospitaliza.dtos.ChamadaEvent;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PainelManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private ChamadaEvent ultimoChamado;
	
	public ChamadaEvent getUltimoChamado() {
		return ultimoChamado;
	}
	
	public void setUltimoChamado(ChamadaEvent ultimoChamado) {
		this.ultimoChamado = ultimoChamado;
	}
}
