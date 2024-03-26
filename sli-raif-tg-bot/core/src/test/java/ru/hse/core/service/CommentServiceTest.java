package ru.hse.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hse.core.dto.CommentDTO;
import ru.hse.core.entity.Comment;
import ru.hse.core.repository.AdminRepository;
import ru.hse.core.repository.CommentRepository;
import ru.hse.notification.SubscriptionService;
import ru.hse.notification.telegram.TgBot;
import ru.hse.statistics.model.IncidentStatus;
import ru.hse.statistics.repository.IncidentRepository;
import ru.hse.statistics.repository.entity.Incident;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private IncidentRepository incidentRepository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private TgBot tgBot;
    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void whenAddCommentaryWithValidIncident_thenSaveCommentary() {
        String userId = "adminUser";
        Long incidentId = 1L;
        IncidentStatus newStatus = IncidentStatus.RESOLVED;
        String content = "Issue resolved.";
        Incident incident = new Incident();
        incident.setIncidentId(incidentId);

        Long expectedCommentId = 42L;
        Comment commentary = new Comment(userId, content, incident, LocalDateTime.now(), newStatus);
        commentary.setId(expectedCommentId);

        when(adminRepository.existsById(userId)).thenReturn(true);
        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(incident));
        when(commentRepository.save(any(Comment.class))).thenReturn(commentary);

        verify(commentRepository).save(any(Comment.class));
        verify(incidentRepository).updateStatus(newStatus, incidentId);
    }


}