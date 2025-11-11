package com.rodrigo.hospitaliza.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class LoginBean {

	private String login;
	private String senha;

	@Inject
	private SecurityContext _securityContext;

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
		
		return _securityContext.authenticate((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(),
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
