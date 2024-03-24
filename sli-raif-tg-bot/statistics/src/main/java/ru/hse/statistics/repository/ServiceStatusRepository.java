package ru.hse.statistics.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hse.statistics.repository.entity.ServiceStatus;

@Repository
public interface ServiceStatusRepository extends JpaRepository<ServiceStatus, Long> {

    @Query("select ss from ServiceStatus ss where ss.endpointName" +
            " = :name and ss.addDate > :dateTime")
    List<ServiceStatus> findAllStatusesAfter(String name, OffsetDateTime dateTime);
}
