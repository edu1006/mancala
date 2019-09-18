package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.service.JobProcessService;
import br.com.petrim.lich.vo.JobProcessDataVo;
import br.com.petrim.lich.vo.JobProcessStatusVo;
import br.com.petrim.lich.vo.JobProcessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/job_process", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class JobProcessResource extends AbstractResource {

    @Autowired
    private JobProcessService jobProcessService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public JobProcessDataVo getJobProcessData() {
        return jobProcessService.getJobProcessData();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobProcess save(@RequestBody JobProcessVo jobProcess) {
        return jobProcessService.save(convert(jobProcess, JobProcess.class));
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long countByFilter(@RequestBody JobProcessVo filter) {
        return jobProcessService.countByFilter(convert(filter, JobProcess.class));
    }

    @RequestMapping(value = "/find/{page}/{max}", method = RequestMethod.POST)
    public List<JobProcess> findByFilter(@RequestBody JobProcessVo filter, @PathVariable("page") Integer page,
                                         @PathVariable("max") Integer max) {
        return jobProcessService.findByFilter(convert(filter, JobProcess.class), page, max);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JobProcess getJobProcessById(@PathVariable("id") Long id) {
        return jobProcessService.getJobProcessById(id);
    }

    @RequestMapping(value = "/update-status", method = RequestMethod.POST)
    public void updateStatus(@RequestBody JobProcessStatusVo jobProcessStatusVo) {
        jobProcessService.updateStatus(jobProcessStatusVo.getIdJob(), jobProcessStatusVo.getStatus());
    }

    @RequestMapping(value = "/find-inner-jobs", method = RequestMethod.GET)
    public List<JobProcess> findInnerJobsEnable() {
        return jobProcessService.findInnerJobsEnable();
    }

    @RequestMapping(value = "/find-enabled", method = RequestMethod.GET)
    public List<JobProcess> findJobsEnabled() {
        return jobProcessService.findJobsEnabled();
    }

}
