package com.rodrigo.hospitaliza.service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeradorSenhaService {
	
	private static Integer LIMITE = 366;
	private AtomicInteger numeroSenha = new AtomicInteger(1);
	
	public String gerarSenhaTriagem() {
		
		int valor = numeroSenha.getAndUpdate(n -> (n >= LIMITE ? 1 : n + 1));
		
		return "T" + String.format("%03d", valor);
	}
	
	public String gerarSenhaAtendimento(){
		return null;
	}
	
}
