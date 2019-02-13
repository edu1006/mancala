package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.JobProcess;

import java.util.List;

public interface JobProcessRepositoryCustom {

    Long countByFilter(JobProcess filter);

    List<JobProcess> findByFilter(JobProcess filter, Integer page, Integer max);

}
