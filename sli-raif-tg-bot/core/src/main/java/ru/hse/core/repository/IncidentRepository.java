package ru.hse.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.core.entity.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
