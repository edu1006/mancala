package br.com.petrim.lich.resources;

import br.com.petrim.lich.enums.AgentTypeEnum;
import br.com.petrim.lich.model.Agent;
import br.com.petrim.lich.service.AgentService;
import br.com.petrim.lich.vo.AgentVo;
import br.com.petrim.lich.vo.StepAgentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/agent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AgentResource extends AbstractResource {

    @Autowired
    private AgentService agentService;

    @RequestMapping(method = RequestMethod.POST)
    public Agent save(@RequestBody AgentVo agent) {
        return agentService.save(convert(agent, Agent.class));
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody Agent filter) {
        return agentService.countByFilter(filter);
    }

    @RequestMapping(value = "/find/{page}/{max}", method = RequestMethod.POST)
    public List<Agent> findByFilter(@RequestBody Agent filter, @PathVariable("page") Integer page,
                                    @PathVariable("max") Integer max) {
        return agentService.findByFilter(filter, page, max);
    }

    @RequestMapping(value = "/find/enable/{type}")
    public List<Agent> findEnableByType(@PathVariable("type") Integer type) {
        return agentService.findEnablesByType(AgentTypeEnum.valueOfId(type));
    }

    @RequestMapping(value = "/find-for-step/{idAgent}", method = RequestMethod.GET)
    public StepAgentVo findAgentForStep(@PathVariable("idAgent") Long id) {
        StepAgentVo stepAgentVo = new StepAgentVo();

        stepAgentVo.setAgent(agentService.findById(id));
        stepAgentVo.setAgentsForType(agentService.findEnablesByType(stepAgentVo.getAgent().getType()));

        return stepAgentVo;
    }

}
