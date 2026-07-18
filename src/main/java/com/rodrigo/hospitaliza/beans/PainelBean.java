package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.rodrigo.hospitaliza.dtos.ChamadaEvent;
import com.rodrigo.hospitaliza.service.PainelManager;

@Named
@SessionScoped
public class PainelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PainelManager painelManager;

	private List<ChamadaEvent> historico = new ArrayList<>();

	private ChamadaEvent atual;

	public void verificarNovasChamadas() {
		ChamadaEvent global = painelManager.getUltimoChamado();

		if (global != null) {
			if (this.atual == null || !this.atual.getSenha().equals(global.getSenha())) {

				if (this.atual != null) {
					ChamadaEvent backupParaHistorico = new ChamadaEvent(this.atual.getSenha(), this.atual.getPaciente(),
							this.atual.getSala());

					this.historico.add(0, backupParaHistorico);
				}

				if (this.historico.size() > 5) {
					this.historico.remove(this.historico.size() - 1);
				}

				this.atual = new ChamadaEvent(global.getSenha(), global.getPaciente(), global.getSala());
			}
		}
	}

	public List<ChamadaEvent> getHistorico() {
		return historico;
	}

	public void setHistorico(List<ChamadaEvent> historico) {
		this.historico = historico;
	}

	public ChamadaEvent getAtual() {
		return atual;
	}

	public void setAtual(ChamadaEvent atual) {
		this.atual = atual;
	}
}