package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.JobProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobProcessRepository extends JpaRepository<JobProcess, Long> {
}
