package com.rodrigo.hospitaliza.beans;

import java.io.Serializable;

import com.rodrigo.hospitaliza.model.Funcionario;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable{
	private static final long serialVersionUID = 1L;

    private Funcionario funcionario;

    public void logar(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void deslogar() {
        this.funcionario = null;
    }

    public boolean isLogado() {
        return funcionario != null;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public boolean HasRole(String role) {
        return isLogado() && funcionario.getFuncao().name().equals(role);
    }
}