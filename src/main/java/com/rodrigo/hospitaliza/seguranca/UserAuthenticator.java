package com.rodrigo.hospitaliza.seguranca;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.service.FuncionarioService;

@ApplicationScoped
public class UserAuthenticator implements IdentityStore {

	@Inject
	private FuncionarioService service;

	@Override
	public CredentialValidationResult validate(Credential credencial) {

		var userCredentials = (UsernamePasswordCredential) credencial;
		var userName = userCredentials.getCaller();
		var password = userCredentials.getPasswordAsString();
		
		Funcionario funcionario = new Funcionario();

		if (userName.equals(service.findByLogin(userName)) && password.equals(service.findByLogin(password))) {
			return new CredentialValidationResult(userName, Set.of(funcionario.getFuncao().name()));
		} else {
			return CredentialValidationResult.INVALID_RESULT;
		}
	}
}
