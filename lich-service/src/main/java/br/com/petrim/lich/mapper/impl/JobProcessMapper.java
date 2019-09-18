package br.com.petrim.lich.mapper.impl;

import br.com.petrim.lich.mapper.AbstractMapper;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.vo.JobProcessVo;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

@Component
public class JobProcessMapper extends AbstractMapper<JobProcess, JobProcessVo> {

    public JobProcessMapper() {
        super(JobProcess.class, JobProcessVo.class);
    }

    @Override
    protected void configure(ClassMapBuilder<JobProcess, JobProcessVo> builder) {
        builder.byDefault();
    }
}
