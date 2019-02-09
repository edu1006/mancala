package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.service.JobProcessService;
import br.com.petrim.lich.vo.JobProcessDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/job_process", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class JobProcessResource {

    @Autowired
    private JobProcessService jobProcessService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public JobProcessDataVo getJobProcessData() {
        return jobProcessService.getJobProcessData();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobProcess save(@RequestBody JobProcess jobProcess) {
        return jobProcessService.save(jobProcess);
    }

}
