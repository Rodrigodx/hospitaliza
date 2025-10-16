package com.rodrigo.hospitaliza.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.rodrigo.hospitaliza.model.Paciente;

@Repository(forEntity = Paciente.class)
public interface PacienteRepository extends EntityRepository<Paciente, Long> {

	Paciente findByCpf(String cpf);
	
}
