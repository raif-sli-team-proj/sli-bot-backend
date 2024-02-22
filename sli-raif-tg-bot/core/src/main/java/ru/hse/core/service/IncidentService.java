package ru.hse.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.core.entity.Incident;
import ru.hse.core.enums.IncidentStatus;
import ru.hse.core.repository.IncidentRepository;
import ru.hse.core.telegram.TgBot;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.service.StatusService;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class IncidentService {

    private final StatusService statusService;
    private final TgBot bot;
    private final IncidentRepository incidentRepository;

    public void checkStatusesAndNotify() {
        Status status = statusService.getServiceStatus("QRC");
        if (status == Status.FAIL) {
            log.info("Service failed.");
            bot.sendMessage("493978992", "❌❌❌ QRC service failed.");
            // stub
            saveIncident("QRC", IncidentStatus.REPORTED, LocalDateTime.now(), null);
        }
        statusService.addStatus(status);
    }

    private void saveIncident(String serviceName, IncidentStatus incidentStatus,
                              LocalDateTime incidentStartTime, LocalDateTime incidentEndTime) {
        var incident = new Incident(serviceName, incidentStatus, incidentStartTime, incidentEndTime);

        incidentRepository.save(incident);
    }
}
