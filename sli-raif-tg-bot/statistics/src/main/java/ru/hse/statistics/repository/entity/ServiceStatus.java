package ru.hse.statistics.repository.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Setter
public class ServiceStatus {
    @jakarta.persistence.Id
    @Id
    private Long id;

    private String serviceName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private OffsetDateTime addDate;

    public enum Status {
        OK,
        FAIL,
        UNKNOWN
    }
}
