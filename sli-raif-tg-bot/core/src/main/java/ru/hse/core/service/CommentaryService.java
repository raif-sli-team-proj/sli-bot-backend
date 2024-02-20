package ru.hse.core.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hse.core.dto.CommentaryDTO;
import ru.hse.core.entity.Commentary;
import ru.hse.core.repository.CommentaryRepository;
import ru.hse.core.repository.IncidentRepository;

@Service
public class CommentaryService {

    private final CommentaryRepository commentaryRepository;
    private final IncidentRepository incidentRepository;

    public CommentaryService(CommentaryRepository commentaryRepository, IncidentRepository incidentRepository) {
        this.commentaryRepository = commentaryRepository;
        this.incidentRepository = incidentRepository;
    }

    public void addCommentary(CommentaryDTO commentaryDTO) {
        var incident = incidentRepository.findById(commentaryDTO.getIncidentId());
        if (incident.isPresent()) {
            var commentary = new Commentary(commentaryDTO.getUserId(), commentaryDTO.getContents(), incident.get());
            commentaryRepository.save(commentary);
            return;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incident does not exist by the given id");
    }
}
