package com.rodrigo.hospitaliza.beans;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class LoginBean {

	private String login;
	private String senha;

	@Inject
	private SecurityContext securityContext;

	public void authenticateUser() throws IOException {

		System.out.println(login + " / " + senha);
		
		AuthenticationStatus result = executeUserAuthentication();
		if (result == AuthenticationStatus.SUCCESS) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/start.xhtml");
			System.out.println("Sucesso!");
		} else if (result == AuthenticationStatus.SEND_CONTINUE) {
			FacesContext.getCurrentInstance().responseComplete();
			System.out.println("CONTINUE!");
		} else if (result == AuthenticationStatus.SEND_FAILURE) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user name and/or password.", null));
		}
	}

	private AuthenticationStatus executeUserAuthentication() {
		
		return securityContext.authenticate((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(),
				(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(),
				AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(login, senha)));
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
