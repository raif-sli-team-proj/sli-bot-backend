package ru.hse.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hse.core.dto.CommentDTO;
import ru.hse.core.dto.CommentResponseDTO;
import ru.hse.core.entity.Comment;
import ru.hse.core.enums.IncidentStatus;
import ru.hse.core.repository.AdminRepository;
import ru.hse.core.repository.CommentRepository;
import ru.hse.core.repository.IncidentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final IncidentRepository incidentRepository;
    private final AdminRepository adminRepository;

    public Long addCommentary(CommentDTO commentDTO) {
        adminCheck(commentDTO);

        var incident = incidentRepository.findById(commentDTO.getIncidentId());
        if (incident.isPresent()) {
            var commentary = new Comment(commentDTO.getUserId(), commentDTO.getContents(), incident.get(), LocalDateTime.now(), commentDTO.getNewIncidentStatus());
            commentRepository.save(commentary);
            incidentRepository.updateStatus(commentDTO.getNewIncidentStatus(), incident.get().getIncidentId());

            if (commentDTO.getNewIncidentStatus() == IncidentStatus.RESOLVED) {
                incidentRepository.updateEndTime(incident.get().getIncidentId());
            }
            return commentary.getId();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incident does not exist by the given id");
    }

    public List<CommentResponseDTO> getCommentsByIncidentId(String id) {
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect path variables. Integers expected.");
        }

        var incident = incidentRepository.findById(longId);

        if (incident.isPresent()) {
            return commentRepository
                    .findAllByIncidentId(incident.get())
                    .stream()
                    .map(x -> new CommentResponseDTO(x.getId(), x.getUserId(), x.getContents(),
                            longId, x.getNewIncidentStatus(), x.getCreationDate()))
                    .toList();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incident does not exist by the given id");
    }

    private void adminCheck(CommentDTO commentDTO) {
        if (!adminRepository.existsById(commentDTO.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
    }
}
