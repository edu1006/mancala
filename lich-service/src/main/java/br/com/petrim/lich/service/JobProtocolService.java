package br.com.petrim.lich.service;

import br.com.petrim.lich.model.JobProtocol;

import java.util.Optional;

public interface JobProtocolService {

    JobProtocol save(JobProtocol jobProtocol);

    Optional<JobProtocol> findById(Long id);

    void updateLastExecutions();

}
