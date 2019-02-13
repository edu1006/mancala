package br.com.petrim.lich.vo;

import br.com.petrim.lich.model.Agent;

import java.util.List;

public class StepAgentVo {

    private Agent agent;
    private List<Agent> agentsForType;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Agent> getAgentsForType() {
        return agentsForType;
    }

    public void setAgentsForType(List<Agent> agentsForType) {
        this.agentsForType = agentsForType;
    }
}
