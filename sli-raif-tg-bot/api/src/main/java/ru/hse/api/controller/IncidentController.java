package ru.hse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.statistics.repository.entity.Incident;
import ru.hse.statistics.service.IncidentService;

import java.util.List;


@RestController
@RequestMapping("api/v1/incident")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping("{pageNumber}/{pageSize}")
    public List<Incident> getIncidentsPaging(@PathVariable("pageNumber") String pageNumber,
                                             @PathVariable("pageSize") String pageSize) {
        return incidentService.getPageOfIncidents(pageNumber, pageSize);
    }

    @GetMapping("{serviceName}/{pageNumber}/{pageSize}")
    public List<Incident> getIncidentsPaging(@PathVariable("serviceName") String serviceName,
                                             @PathVariable("pageNumber") String pageNumber,
                                             @PathVariable("pageSize") String pageSize) {
        return incidentService.getPageOfIncidentsByService(serviceName, pageNumber, pageSize);
    }
}
