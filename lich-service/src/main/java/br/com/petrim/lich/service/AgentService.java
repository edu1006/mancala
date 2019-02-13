package br.com.petrim.lich.service;

import br.com.petrim.lich.enums.AgentTypeEnum;
import br.com.petrim.lich.model.Agent;

import java.util.List;

public interface AgentService {

    Agent save(Agent agent);

    Long countByFilter(Agent filter);

    List<Agent> findByFilter(Agent filter, Integer page, Integer max);

    List<Agent> findEnablesByType(AgentTypeEnum type);

    Agent findById(Long id);

}
