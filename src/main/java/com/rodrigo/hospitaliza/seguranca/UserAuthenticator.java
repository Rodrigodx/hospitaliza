package com.rodrigo.hospitaliza.seguranca;

import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.repository.FuncionarioRepository;
import com.rodrigo.hospitaliza.service.FuncionarioService;

@ApplicationScoped
public class UserAuthenticator implements IdentityStore {
	
	@Inject
	private FuncionarioRepository repository;
	
	@Inject
	private Pbkdf2PasswordHash passwordHash;
	
	@Override
	public int priority() {
	    return 30;
	}

	public CredentialValidationResult validate(UsernamePasswordCredential credencial) {

		var userName = credencial.getCaller();
		var password = credencial.getPasswordAsString();
		
		Optional<Funcionario> funcOpt = repository.findByLogin(userName);
		if(funcOpt.isPresent()) {
			Funcionario funcionario = funcOpt.get();
			if(passwordHash.verify(password.toCharArray(), funcionario.getSenha())) {
				return new CredentialValidationResult(userName, Set.of(funcionario.getFuncao().name()));
			}
		}
			return CredentialValidationResult.INVALID_RESULT;
	}
}
