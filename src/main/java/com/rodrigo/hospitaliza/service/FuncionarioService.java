package com.rodrigo.hospitaliza.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.repository.FuncionarioRepositoryDAO;

@ApplicationScoped
public class FuncionarioService {

	@Inject
	private FuncionarioRepositoryDAO repository;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private Pbkdf2PasswordHash passwordHash;

	@Transactional
	public Funcionario save(Funcionario funcionario) {
		
		String cpfComMascara = funcionario.getCpf();
		String senhaComHash = passwordHash.generate(funcionario.getSenha().toCharArray());
		
		
		
		if(cpfComMascara != null && senhaComHash != null) {
			String cpfFormatado = cpfComMascara.replaceAll("[^0-9]", "");
			
			funcionario.setCpf(cpfFormatado);
			funcionario.setSenha(senhaComHash);
			
		}
		
		return repository.save(funcionario);
	}
	
	public Funcionario getUserLogged() {
		return repository.getUserLogged();
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario findById(Long id) {
		return repository.findById(id);
	}
	
	public Funcionario findByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Transactional
	public Funcionario update(Funcionario funcionario) {
		Funcionario funcionarioExistente = findById(funcionario.getId());
		
		if(funcionarioExistente == null) {
			throw new IllegalArgumentException("Funcionario não encontrado.");
		}
		
		funcionarioExistente.setNome(funcionario.getNome());
		funcionarioExistente.setCpf(funcionario.getCpf());
		funcionarioExistente.setFuncao(funcionario.getFuncao());
		funcionarioExistente.setLogin(funcionario.getLogin());
		funcionarioExistente.setSenha(funcionario.getSenha());
		
		return repository.save(funcionarioExistente);
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}
}
