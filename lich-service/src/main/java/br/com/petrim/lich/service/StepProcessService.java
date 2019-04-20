package br.com.petrim.lich.service;

import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.StepProcess;

import java.util.List;

public interface StepProcessService {

    void saveStepsProcesses(JobProcess jobProcess);

    List<StepProcess> findByIdJobProcess(Long idJobProcess);

}
