package br.com.petrim.lich.repository;

import br.com.petrim.lich.model.JobProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JobProtocolRepository extends JpaRepository<JobProtocol, Long> {

    @Query("From JobProtocol jp " +
            "Where jp.parentProtocol is null " +
            "and jp.dateEnd > :dateEnd " +
            "order by jp.dateEnd desc ")
    List<JobProtocol> findEndsProtocols(@Param("dateEnd") Date dateEnd);

}
