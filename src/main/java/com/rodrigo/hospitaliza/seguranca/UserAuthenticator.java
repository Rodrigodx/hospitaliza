package com.rodrigo.hospitaliza.seguranca;

import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.repository.FuncionarioRepositoryDAO;

@ApplicationScoped
public class UserAuthenticator implements IdentityStore {

    @Inject
    private FuncionarioRepositoryDAO repository;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Override
    public int priority() {
        return 30;
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential instanceof UsernamePasswordCredential) {

        	UsernamePasswordCredential credencial = (UsernamePasswordCredential) credential;
        	
            var userName = credencial.getCaller();
            var password = credencial.getPasswordAsString();

            Optional<Funcionario> funcOpt =
                    Optional.ofNullable(repository.findByLogin(userName));

            if (funcOpt.isPresent()) {

                Funcionario funcionario = funcOpt.get();

                if (passwordHash.verify(password.toCharArray(), funcionario.getSenha())) {

                    return new CredentialValidationResult(
                            userName,
                            Set.of(funcionario.getFuncao().name())
                    );
                }
            }
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}