package ru.hse.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
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
    public void whenAddCommentaryWithInvalidIncident_thenThrowException() {
        String userId = "adminUser";
        Long incidentId = 1L;
        IncidentStatus newStatus = IncidentStatus.RESOLVED;
        CommentDTO commentDTO = new CommentDTO(userId, "Issue resolved.", incidentId, newStatus);

        when(adminRepository.existsById(userId)).thenReturn(true);
        when(incidentRepository.findById(incidentId)).thenReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(
                ResponseStatusException.class,
                () -> commentService.addCommentary(commentDTO),
                "Expected addCommentary to throw, but it didn't"
        );

        assertSame(thrown.getStatusCode(), HttpStatusCode.valueOf(400));
    }
}