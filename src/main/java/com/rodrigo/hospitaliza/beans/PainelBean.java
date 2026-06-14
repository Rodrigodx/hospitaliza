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
			// Se a TV está vazia OU se o paciente global que acabou de chegar tem uma senha
			// diferente da TV
			if (this.atual == null || !this.atual.getSenha().equals(global.getSenha())) {

				System.out.println(">>> [PAINEL TV] Nova senha detectada: " + global.getSenha());

				// 1. Se já existia um paciente na tela, vamos movê-lo para o histórico
				if (this.atual != null) {

					// CRUCIAL: Criamos um NOVO objeto com os dados antigos para isolar a referência
					// de memória.
					// Isso impede que o JSF se confunda e duplique ou limpe a tabela do histórico.
					ChamadaEvent backupParaHistorico = new ChamadaEvent(this.atual.getSenha(), this.atual.getPaciente(),
							this.atual.getSala());

					// Adiciona sempre na primeira posição (topo da tabela)
					this.historico.add(0, backupParaHistorico);

					System.out.println(">>> [PAINEL TV] Sucesso ao mover para o histórico. Total de itens: "
							+ this.historico.size());
				}

				// 2. Controla o limite máximo de itens no histórico (Se passar de 5, remove o
				// mais antigo)
				if (this.historico.size() > 5) {
					this.historico.remove(this.historico.size() - 1);
					System.out.println(">>> [PAINEL TV] Histórico limitou em 5 itens. Removendo o mais velho.");
				}

				// 3. Cria uma instância isolada também para o painel atual da TV
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