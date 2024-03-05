package ru.hse.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {

    public Comment(String userId, String contents, Incident incidentId, LocalDateTime localDateTime) {
        this.userId = userId;
        this.contents = contents;
        this.incidentId = incidentId;
        this.creationDate = localDateTime;
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
}
