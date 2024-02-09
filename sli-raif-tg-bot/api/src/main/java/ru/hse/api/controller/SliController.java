package ru.hse.api.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.api.dto.SliServicesResponse;

@RestController
@RequestMapping("api/v1/sli")
public class SliController {

    @GetMapping("/services")
    public SliServicesResponse getServices() {
        return new SliServicesResponse(Set.of());
    }

    @GetMapping("/{service}")
    public void getSli(@PathVariable String service) {

    }
}
