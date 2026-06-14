package com.rodrigo.hospitaliza.dtos;

public class ChamadaEvent {

	private String senha;
	private String paciente;
	private String sala;
	
	public ChamadaEvent(String senha, String paciente, String sala) {
        this.senha = senha;
        this.paciente = paciente;
        this.sala = sala;
    }

    public String getSenha() { return senha; }
    public String getPaciente() { return paciente; }
    public String getSala() { return sala; }

	@Override
	public String toString() {
		return "ChamadaEvent [senha=" + senha + ", paciente=" + paciente + ", sala=" + sala + "]";
	}
    
    
}
