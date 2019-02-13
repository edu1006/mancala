package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.JobProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobProcessRepository extends JpaRepository<JobProcess, Long>, JobProcessRepositoryCustom {

    @Query("From JobProcess j " +
            "join fetch j.stepsProcesses sp " +
            "left join fetch sp.stepsParallels spl " +
            "where j.id = :id ")
    Optional<JobProcess> loadById(@Param("id") Long id);

}
