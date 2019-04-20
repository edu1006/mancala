package br.com.petrim.lich.resources;

import br.com.petrim.lich.batch.executor.JobExecutor;
import br.com.petrim.lich.vo.JobExecutionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/job_execution", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class JobExecutionResource extends AbstractResource {

    @Autowired
    private JobExecutor jobExecutor;

    @RequestMapping(method = RequestMethod.PUT)
    public void execute(@RequestBody JobExecutionVo jobExecutionVo) {
        jobExecutor.executeJob(jobExecutionVo.getIdJobProcess(), getCurrentUser());
    }

}
