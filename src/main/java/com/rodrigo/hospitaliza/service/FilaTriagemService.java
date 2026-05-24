package com.rodrigo.hospitaliza.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.enterprise.context.ApplicationScoped;

import com.rodrigo.hospitaliza.model.Atendimento;

@ApplicationScoped
public class FilaTriagemService {
	
	private Queue<Atendimento> filaAtendimento = new ConcurrentLinkedQueue<Atendimento>();
	
	public void adicionarAtendimentoFila(Atendimento atendimento) {
		filaAtendimento.offer(atendimento);
		System.out.println("Adicionado na fila");
	}
	
	public Atendimento chamarProximo() {
		Atendimento atendimento = filaAtendimento.poll();
		System.out.println("Chamando: " + atendimento.getPaciente().getNome());
		return atendimento;
	}
}
