package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.enums.AgentTypeConnEnum;
import br.com.petrim.lich.enums.AgentTypeEnum;
import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.Agent;
import br.com.petrim.lich.repository.AgentRepository;
import br.com.petrim.lich.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("agentService")
public class AgentServiceImpl extends AbstractService implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent save(Agent agent) {
        verifyAgent(agent);

        if (agent.getId() == null) {
            agent.setDateInsert(new Date());
            agent.setTypeConnection(AgentTypeConnEnum.REST);
        }

        return agentRepository.save(agent);
    }

    private void verifyAgent(Agent agent) {
        List<Long> idsAgentExists = agentRepository.getIdByNameOrAddress(agent.getName(), agent.getAddress(), agent.getPort());

            if ((agent.getId() == null && !idsAgentExists.isEmpty()) ||
                    (agent.getId() != null && containsAnotherId(idsAgentExists, agent.getId()))) {

                throw new BusinessException("agent.exists");
        }

    }

    private Boolean containsAnotherId(List<Long> ids, Long id) {
        return (ids.size() > 1) || (ids.size() == 1 && !ids.contains(id));
    }

    @Override
    public Long countByFilter(Agent filter) {
        return agentRepository.countByFilter(filter);
    }

    @Override
    public List<Agent> findByFilter(Agent filter, Integer page, Integer max) {
        return agentRepository.findByFilter(filter, page, max);
    }

    @Override
    public List<Agent> findEnablesByType(AgentTypeEnum type) {
        if (type == null) {
            throw new BusinessException("agent.type.invalid");
        }

        List<Agent> agents = agentRepository.findByStatusAndType(StatusEnum.ENABLED, type);

        if (agents == null || agents.isEmpty()) {
            throw new BusinessException("agent.not.exists.by.type");
        }

        return agents;
    }

    @Override
    public Agent findById(Long id) {
        Optional<Agent> optional = agentRepository.findById(id);

        if (!optional.isPresent()) {
            throw new BusinessException("");
        }

        return optional.get();
    }

    @Override
    public Agent findToRunStep(Long id) {
        return agentRepository.findToRunStep(id);
    }
}
