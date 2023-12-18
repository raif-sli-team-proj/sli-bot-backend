package ru.hse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.hse.service.StatusService;
import ru.hse.dto.ServiceDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public Mono<List<ServiceDTO>> getStatus() {
        return statusService.getFPSServiceStatuses();
    }
}
