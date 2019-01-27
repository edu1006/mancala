package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.Agent;
import br.com.petrim.lich.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/agent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AgentResource {

    @Autowired
    private AgentService agentService;

    @RequestMapping(method = RequestMethod.POST)
    public Agent save(@RequestBody Agent agent) {
        return agentService.save(agent);
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

}
