package ru.hse.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.hse.core.enums.IncidentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident")
@Data
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id")
    private Long incidentId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "incident_status")
    @Enumerated(EnumType.STRING)
    private IncidentStatus incidentStatus;

    @Column(name = "incident_start_time")
    private LocalDateTime incidentStartTime;

    @Column(name = "incident_end_time")
    private LocalDateTime incidentEndTime;
}
