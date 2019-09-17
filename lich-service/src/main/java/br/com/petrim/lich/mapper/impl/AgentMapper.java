package br.com.petrim.lich.mapper.impl;

import br.com.petrim.lich.mapper.AbstractMapper;
import br.com.petrim.lich.model.Agent;
import br.com.petrim.lich.vo.AgentVo;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper extends AbstractMapper<Agent, AgentVo> {

    public AgentMapper() {
        super(Agent.class, AgentVo.class);
    }

    @Override
    protected void configure(ClassMapBuilder<Agent, AgentVo> builder) {
        builder.byDefault();
    }
}
