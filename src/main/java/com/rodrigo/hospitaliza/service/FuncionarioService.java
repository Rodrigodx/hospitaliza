package com.rodrigo.hospitaliza.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import com.rodrigo.hospitaliza.model.Funcionario;
import com.rodrigo.hospitaliza.repository.FuncionarioRepository;

@ApplicationScoped
public class FuncionarioService {

	/*@Inject
	private FuncionarioRepositoryDAO repository;*/
	
	@Inject
	private FuncionarioRepository repository;

	@Inject
	private SecurityContext securityContext;

	@Transactional
	public Funcionario save(Funcionario funcionario) {
		
		String cpfComMascara = funcionario.getCpf();
		
		
		
		if(cpfComMascara != null) {
			String cpfFormatado = cpfComMascara.replaceAll("[^0-9]", "");

			funcionario.setCpf(cpfFormatado);
			
		}
		
		return repository.save(funcionario);
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario findById(Long id) {
		return repository.findBy(id);
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
		repository.remove(findById(id));
	}
}
