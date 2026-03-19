package com.rodrigo.hospitaliza.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.enterprise.context.ApplicationScoped;

import com.rodrigo.hospitaliza.model.Atendimento;

@ApplicationScoped
public class FilaTriagemService {
	
	private Queue<Atendimento> filaAtendimento = new ConcurrentLinkedQueue<Atendimento>();
	
	public void adicionarAtendimentoFila(Atendimento atendiemento) {
		filaAtendimento.offer(atendiemento);
	}
	
	public Atendimento chamarProximo() {
		Atendimento atendimento = filaAtendimento.poll();
		
		return atendimento;
	}

}
