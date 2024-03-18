package ru.hse.api.controller;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.api.dto.GetSliResponse;
import ru.hse.api.dto.SliServicesResponse;
import ru.hse.statistics.service.SliService;

@RestController
@RequestMapping("api/v1/sli")
@RequiredArgsConstructor
public class SliController {
    private final SliService sliService;

    @GetMapping("/services")
    public SliServicesResponse getServices() {
        return new SliServicesResponse(Set.of());
    }

    @GetMapping("/{service}")
    public GetSliResponse getSli(
            @PathVariable String service,
            @RequestParam String frame
    ) {
        return new GetSliResponse(sliService.getServiceSli(service, frame), service, frame);
    }
}
