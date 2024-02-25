package ru.hse.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.hse.core.entity.Incident;
import ru.hse.core.enums.IncidentStatus;
import ru.hse.core.repository.IncidentRepository;
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
        int pageSizeInt;
        int pageNumberInt;

        try {
            pageNumberInt = Integer.parseInt(pageNumber);
            pageSizeInt = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect path variables. Integers expected.");
        }

        Pageable pageable = PageRequest.of(pageNumberInt, pageSizeInt);

        return incidentRepository.findAllBy(pageable).getContent();
    }
}
