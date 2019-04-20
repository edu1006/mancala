package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.enums.TypeStepProcessEnum;
import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.repository.StepProcessRepository;
import br.com.petrim.lich.service.StepProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            stepProcess.setIdJobProcess(jobProcess.getId());
        });

        stepProcessRepository.saveAll(jobProcess.getStepsProcesses());
        saveStepProcessParallels(jobProcess.getStepsProcesses());
    }

    private void saveStepProcessParallels(Set<StepProcess> stepsProcesses) {
        Set<StepProcess> parallels = stepsProcesses.stream()
                .filter(stepProcess -> TypeStepProcessEnum.STEP_PARALLEL.equals(stepProcess.getType()))
                .collect(Collectors.toSet());

        if (parallels != null && !parallels.isEmpty()) {
            for (StepProcess parallel : parallels) {
                parallel.getStepsParallels().forEach(stepProcess -> {
                    stepProcess.setIdStepParallel(parallel.getId());
                });

                stepProcessRepository.saveAll(parallel.getStepsParallels());
            }
        }
    }

    @Override
    public List<StepProcess> findByIdJobProcess(Long idJobProcess) {
        return null;
    }
}
