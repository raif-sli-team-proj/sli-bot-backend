package ru.hse.statistics.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hse.statistics.repository.entity.Incident;
import ru.hse.statistics.model.IncidentStatus;
import ru.hse.statistics.repository.IncidentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public void saveIncident(String serviceName, IncidentStatus incidentStatus,
                             LocalDateTime incidentStartTime, LocalDateTime incidentEndTime) {
        var incident = new Incident(serviceName, incidentStatus, incidentStartTime, incidentEndTime);

        incidentRepository.save(incident);
    }

    public List<Incident> getPageOfIncidents(String pageNumber, String pageSize) {
        var parsedPathVariablesList = parsePathVariables(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(parsedPathVariablesList.get(0), parsedPathVariablesList.get(1),
                Sort.by("incidentStartTime").descending());

        return incidentRepository.findAllBy(pageable).getContent();
    }

    public List<Incident> getPageOfIncidentsByService(String serviceName, String pageNumber, String pageSize) {
        var parsedPathVariablesList = parsePathVariables(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(parsedPathVariablesList.get(0), parsedPathVariablesList.get(1),
                Sort.by("incidentStartTime").descending());

        return incidentRepository.findAllByServiceName(serviceName, pageable).getContent();
    }

    private List<Integer> parsePathVariables(String pageNumber, String pageSize) {
        int pageSizeInt;
        int pageNumberInt;

        try {
            pageNumberInt = Integer.parseInt(pageNumber);
            pageSizeInt = Integer.parseInt(pageSize);

            return List.of(pageNumberInt, pageSizeInt);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect path variables. Integers expected.");
        }
    }
}
