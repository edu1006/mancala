package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.JobProtocol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobProtocolRepository extends JpaRepository<JobProtocol, Long> {
}
