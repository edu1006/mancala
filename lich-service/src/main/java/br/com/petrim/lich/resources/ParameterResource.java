package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.Parameter;
import br.com.petrim.lich.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/parameter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ParameterResource {

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(method = RequestMethod.PUT)
    public Parameter save(@RequestBody Parameter parameter) {
        return parameterService.save(parameter);
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody Parameter filter) {
        return parameterService.countByFilter(filter);
    }

    @RequestMapping(value = "/find/{page}/{max}", method = RequestMethod.POST)
    public List<Parameter> findByFilter(@RequestBody Parameter filter, @PathVariable("page") Integer page,
                                        @PathVariable("max") Integer max) {
        return parameterService.findByFilter(filter, page, max);
    }

}
