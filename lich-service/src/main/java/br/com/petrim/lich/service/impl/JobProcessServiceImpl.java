package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.enums.PeriodicityEnum;
import br.com.petrim.lich.enums.TypeExecutionEnum;
import br.com.petrim.lich.enums.WeekDayEnum;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.repository.JobProcessRepository;
import br.com.petrim.lich.service.JobProcessService;
import br.com.petrim.lich.service.StepProcessService;
import br.com.petrim.lich.util.MessageUtil;
import br.com.petrim.lich.vo.EnumValueVo;
import br.com.petrim.lich.vo.JobProcessDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("jobProcessService")
public class JobProcessServiceImpl extends AbstractService implements JobProcessService {

    @Autowired
    private StepProcessService stepProcessService;

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

        return jobProcessDataVo;
    }

    @Override
    public JobProcess save(JobProcess jobProcess) {

        loadUserInsertUpdate(jobProcess);

        if (jobProcess.getTypeExecution() != null &&
                jobProcess.getTypeExecution().equals(TypeExecutionEnum.MANUAL)) {

            jobProcess.setPeriodicityStartDate(null);
            jobProcess.setPeriodicityEndDate(null);
        }

        jobProcess = jobProcessRepository.save(jobProcess);
        stepProcessService.saveStepsProcesses(jobProcess);

        return jobProcess;
    }
}
