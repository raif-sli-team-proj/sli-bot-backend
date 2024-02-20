package ru.hse.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hse.core.entity.Incident;
import ru.hse.core.enums.IncidentStatus;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    @Modifying
    @Query("update Incident i set i.incidentStatus = ?1")
    void updateStatus(IncidentStatus incidentStatus);
}
