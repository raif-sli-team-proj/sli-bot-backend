package ru.hse.statistics.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.statistics.model.IncidentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident")
@Data
@NoArgsConstructor
public class Incident {

    public Incident(String serviceName, IncidentStatus incidentStatus,
                    LocalDateTime incidentStartTime, LocalDateTime incidentEndTime) {
        this.serviceName = serviceName;
        this.incidentStatus = incidentStatus;
        this.incidentStartTime = incidentStartTime;
        this.incidentEndTime = incidentEndTime;
    }

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
