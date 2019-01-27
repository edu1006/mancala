package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.Agent;

import java.util.List;

public interface AgentRepositoryCustom {

    Long countByFilter(Agent filter);

    List<Agent> findByFilter(Agent filter, Integer page, Integer max);

}
