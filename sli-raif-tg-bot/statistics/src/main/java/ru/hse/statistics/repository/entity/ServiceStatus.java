package ru.hse.statistics.repository.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.hse.statistics.model.Status;

@Entity
@Table(name = "service_status")
@Setter
@Getter
public class ServiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "endpoint_name")
    private String endpointName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "add_date")
    private OffsetDateTime addDate;
}
