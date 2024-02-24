package ru.hse.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hse.core.dto.CommentDTO;
import ru.hse.core.entity.Comment;
import ru.hse.core.enums.IncidentStatus;
import ru.hse.core.repository.AdminRepository;
import ru.hse.core.repository.CommentRepository;
import ru.hse.core.repository.IncidentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final IncidentRepository incidentRepository;
    private final AdminRepository adminRepository;

    public void addCommentary(CommentDTO commentDTO) {
        adminCheck(commentDTO);

        var incident = incidentRepository.findById(commentDTO.getIncidentId());
        if (incident.isPresent()) {
            var commentary = new Comment(commentDTO.getUserId(), commentDTO.getContents(), incident.get());
            commentRepository.save(commentary);
            incidentRepository.updateStatus(commentDTO.getNewIncidentStatus(), incident.get().getIncidentId());

            if (commentDTO.getNewIncidentStatus() == IncidentStatus.RESOLVED) {
                incidentRepository.updateEndTime(incident.get().getIncidentId());
            }
            return;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incident does not exist by the given id");
    }

    private void adminCheck(CommentDTO commentDTO) {
        if (!adminRepository.existsById(commentDTO.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
    }
}
