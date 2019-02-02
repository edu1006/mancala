package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.repository.StepProcessRepository;
import br.com.petrim.lich.service.StepProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stepProcessService")
public class StepProcessServiceImpl extends AbstractService implements StepProcessService {

    @Autowired
    private StepProcessRepository stepProcessRepository;

    @Override
    public void saveStepsProcesses(JobProcess jobProcess) {

        if (jobProcess.getStepsProcesses() == null || jobProcess.getStepsProcesses().isEmpty()) {
            throw new BusinessException("steps.empty");
        }

        jobProcess.getStepsProcesses().forEach(stepProcess -> {

            loadUserInsertUpdate(stepProcess);

            stepProcess.setIdJobProcess(jobProcess.getId());
        });

        stepProcessRepository.saveAll(jobProcess.getStepsProcesses());
    }
}
