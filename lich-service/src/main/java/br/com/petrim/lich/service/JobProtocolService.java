package br.com.petrim.lich.service;

import br.com.petrim.lich.model.JobProtocol;
import br.com.petrim.lich.vo.JobExecsResultVo;

import java.util.Optional;

public interface JobProtocolService {

    JobProtocol save(JobProtocol jobProtocol);

    Optional<JobProtocol> findById(Long id);

    JobExecsResultVo findLastExecutions();

    void updateLastExecutions();

}
