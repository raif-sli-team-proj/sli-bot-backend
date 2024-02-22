package ru.hse.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.core.entity.Incident;
import ru.hse.core.enums.IncidentStatus;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    @Modifying
    @Transactional
    @Query("update Incident i set i.incidentStatus = ?1 where i.incidentId = ?2")
    void updateStatus(IncidentStatus incidentStatus, Long id);

    @Modifying
    @Transactional
    @Query("update Incident i set i.incidentEndTime = now() where i.incidentId = ?1")
    void updateEndTime(Long id);
}
