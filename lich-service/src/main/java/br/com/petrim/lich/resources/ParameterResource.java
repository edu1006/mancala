package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.Parameter;
import br.com.petrim.lich.service.ParameterService;
import br.com.petrim.lich.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/parameter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ParameterResource extends AbstractResource {

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(method = RequestMethod.PUT)
    public Parameter save(@RequestBody ParameterVo parameter) {
        return parameterService.save(convert(parameter, Parameter.class));
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody ParameterVo filter) {
        return parameterService.countByFilter(convert(filter, Parameter.class));
    }

    @RequestMapping(value = "/find/{page}/{max}", method = RequestMethod.POST)
    public List<Parameter> findByFilter(@RequestBody ParameterVo filter, @PathVariable("page") Integer page,
                                        @PathVariable("max") Integer max) {
        return parameterService.findByFilter(convert(filter, Parameter.class), page, max);
    }

    @RequestMapping(value = "/find-enabled", method = RequestMethod.GET)
    public List<Parameter> findEnabled() {
        return parameterService.findEnabled();
    }

}
