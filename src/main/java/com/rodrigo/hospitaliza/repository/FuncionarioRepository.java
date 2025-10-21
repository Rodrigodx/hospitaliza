package com.rodrigo.hospitaliza.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.rodrigo.hospitaliza.model.Funcionario;

@Repository(forEntity = Funcionario.class)
public interface FuncionarioRepository extends EntityRepository<Funcionario, Long>  {

}
