package br.com.petrim.lich.service;

import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.vo.JobProcessDataVo;

public interface JobProcessService {

    JobProcessDataVo getJobProcessData();

    JobProcess save(JobProcess jobProcess);

}
