package ru.hse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.hse.api.dto.ServiceDTO;
import ru.hse.api.service.RaifStatusService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/status")
public class StatusController {

    private final RaifStatusService statusService;

    @GetMapping
    public Mono<List<ServiceDTO>> getStatus() {
        return statusService.getFPSServiceStatuses();
    }
}
