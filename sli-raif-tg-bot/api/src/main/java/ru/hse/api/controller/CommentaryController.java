package ru.hse.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.core.dto.CommentaryDTO;
import ru.hse.core.service.CommentaryService;

@RestController
@RequestMapping(path = "api/v1/commentary")
public class CommentaryController {

    private final CommentaryService commentaryService;

    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @PostMapping
    public void addComment(@RequestBody CommentaryDTO commentaryDTO) {
        commentaryService.addCommentary(commentaryDTO);
    }
}
