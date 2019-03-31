package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.enums.*;
import br.com.petrim.lich.exception.BusinessException;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.repository.JobProcessRepository;
import br.com.petrim.lich.service.JobProcessService;
import br.com.petrim.lich.service.ParameterService;
import br.com.petrim.lich.service.StepProcessService;
import br.com.petrim.lich.util.MessageUtil;
import br.com.petrim.lich.vo.EnumValueVo;
import br.com.petrim.lich.vo.JobProcessDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("jobProcessService")
public class JobProcessServiceImpl extends AbstractService implements JobProcessService {

    @Autowired
    private StepProcessService stepProcessService;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private JobProcessRepository jobProcessRepository;

    @Override
    public JobProcessDataVo getJobProcessData() {
        JobProcessDataVo jobProcessDataVo = new JobProcessDataVo();

        // get periodicity data
        List<EnumValueVo<Integer>> periodicities = Stream.of(PeriodicityEnum.values())
                .map(periodicityEnum -> new EnumValueVo<>(periodicityEnum.getId(), MessageUtil.getMessage(periodicityEnum.getLabel())))
                .collect(Collectors.toList());

        jobProcessDataVo.setPeriodicities(periodicities);

        // get week day
        List<EnumValueVo<Integer>> weekDays = Stream.of(WeekDayEnum.values())
                .map(weekDayEnum -> new EnumValueVo<Integer>(weekDayEnum.getId(), MessageUtil.getMessage(weekDayEnum.getLabel())))
                .collect(Collectors.toList());

        jobProcessDataVo.setWeekDays(weekDays);

        jobProcessDataVo.setParameters(parameterService.findEnabled());

        return jobProcessDataVo;
    }

    @Override
    @Transactional
    public JobProcess save(JobProcess jobProcess) {

        if (jobProcess.getTypeExecution() != null &&
                jobProcess.getTypeExecution().equals(TypeExecutionEnum.MANUAL)) {

            jobProcess.setPeriodicityStartDate(null);
            jobProcess.setPeriodicityEndDate(null);
        }

        jobProcessRepository.save(jobProcess);
        stepProcessService.saveStepsProcesses(jobProcess);

        return jobProcess;
    }

    @Override
    public Long countByFilter(JobProcess filter) {
        return jobProcessRepository.countByFilter(filter);
    }

    @Override
    public List<JobProcess> findByFilter(JobProcess filter, Integer page, Integer max) {
        return jobProcessRepository.findByFilter(filter, page, max);
    }

    @Override
    public JobProcess getJobProcessById(Long id) {
        Optional<JobProcess> optional = jobProcessRepository.loadById(id);

        if (!optional.isPresent()) {
            throw new BusinessException("");
        }

        return optional.get();
    }

    @Override
    @Transactional
    public void updateStatus(Long id, StatusEnum status) {
        jobProcessRepository.updateStatus(id, status);
    }

    @Override
    public List<JobProcess> findInnerJobsEnable() {
        return jobProcessRepository.findInnerJobsEnable(YesNoEnum.YES, StatusEnum.ENABLED);
    }

    @Override
    public List<JobProcess> findJobsEnabled() {
        return jobProcessRepository.loadEnabled(StatusEnum.ENABLED);
    }
}
