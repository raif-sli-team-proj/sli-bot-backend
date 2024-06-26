package ru.hse.statistics.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.statistics.repository.entity.Incident;
import ru.hse.statistics.model.IncidentStatus;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    @Modifying
    @Transactional
    @Query("update Incident i set i.incidentStatus = ?1 where i.incidentId = ?2")
    void updateStatus(IncidentStatus incidentStatus, Long id);

    @Modifying
    @Transactional
    @Query(value = "update incident set incident_end_time = now() where incident_id = :id", nativeQuery = true)
    void updateEndTime(@Param("id") Long id);

    Page<Incident> findAllBy(Pageable pageable);

    Page<Incident> findAllByServiceName(String serviceName, Pageable pageable);
}
