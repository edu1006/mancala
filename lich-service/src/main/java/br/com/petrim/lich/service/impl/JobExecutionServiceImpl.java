package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.service.JobExecutionService;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;

public class JobExecutionServiceImpl extends AbstractService implements JobExecutionService {

    @Autowired
    private JobExecutionDao jobExecutionDao;

    @Override
    public void findExecutions() {

    }
}
