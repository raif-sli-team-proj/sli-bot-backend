package ru.hse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hse.core.dto.CommentDTO;
import ru.hse.core.dto.CommentResponseDTO;
import ru.hse.core.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Long addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addCommentary(commentDTO);
    }

    @GetMapping("{incidentId}")
    public List<CommentResponseDTO> getCommentsByIncidentId(@PathVariable("incidentId") String id) {
        return commentService.getCommentsByIncidentId(id);
    }
}
