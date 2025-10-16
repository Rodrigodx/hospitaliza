package com.rodrigo.hospitaliza.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.rodrigo.hospitaliza.model.Atendimento;

@Repository(forEntity = Atendimento.class)
public interface AtendimentoRepository extends EntityRepository<Atendimento, Long> {

}
