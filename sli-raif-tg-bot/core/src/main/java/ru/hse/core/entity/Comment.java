package ru.hse.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.core.enums.IncidentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {

    public Comment(String userId, String contents, Incident incidentId, LocalDateTime localDateTime, IncidentStatus newIncidentStatus) {
        this.userId = userId;
        this.contents = contents;
        this.incidentId = incidentId;
        this.creationDate = localDateTime;
        this.newIncidentStatus = newIncidentStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "contents")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incident_id", referencedColumnName = "incident_id")
    private Incident incidentId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "new_incident_status")
    private IncidentStatus newIncidentStatus;
}
