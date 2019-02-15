package br.com.petrim.lich.service;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.vo.JobProcessDataVo;

import java.util.List;

public interface JobProcessService {

    JobProcessDataVo getJobProcessData();

    JobProcess save(JobProcess jobProcess);

    Long countByFilter(JobProcess filter);

    List<JobProcess> findByFilter(JobProcess filter, Integer page, Integer max);

    JobProcess getJobProcessById(Long id);

    void updateStatus(Long id, StatusEnum status);

}
