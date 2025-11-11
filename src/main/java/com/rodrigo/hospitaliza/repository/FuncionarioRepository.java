package com.rodrigo.hospitaliza.repository;

import java.util.Optional;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import com.rodrigo.hospitaliza.model.Funcionario;

@Repository(forEntity = Funcionario.class)
public interface FuncionarioRepository extends EntityRepository<Funcionario, Long>  {
	
	@Query("select f from Funcionario f where f.login = ?1")
	public abstract Optional<Funcionario> findByLogin(String login);
}