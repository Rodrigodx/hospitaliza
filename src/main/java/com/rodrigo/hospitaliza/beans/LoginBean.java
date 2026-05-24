package com.rodrigo.hospitaliza.beans;

import java.io.IOException;
import java.io.Serializable;

import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.repository.FuncionarioRepositoryDAO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	
	@Inject
	private FuncionarioRepositoryDAO repository;

	@Inject
	private Pbkdf2PasswordHash passwordHash;

	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public void authenticateUser() throws IOException {
	    Funcionario f = repository.findByLogin(login);

	    if (f != null && passwordHash.verify(senha.toCharArray(), f.getSenha())) {
	        usuarioLogado.logar(f);
	        FacesContext.getCurrentInstance().getExternalContext().redirect("start.xhtml");
	    } else {
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciais Inválidas", null));
	    }
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}