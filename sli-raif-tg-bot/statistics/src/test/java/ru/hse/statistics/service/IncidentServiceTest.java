package ru.hse.statistics.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import ru.hse.statistics.model.IncidentStatus;
import ru.hse.statistics.repository.IncidentRepository;
import ru.hse.statistics.repository.entity.Incident;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    @Mock
    private IncidentRepository incidentRepository;

    @InjectMocks
    private IncidentService incidentService;

    @Test
    public void testParsingPathVariables() {
        assertThrows(ResponseStatusException.class,
                () -> incidentService.getPageOfIncidents("sfkas", "fasf"));
        assertThrows(ResponseStatusException.class,
                () -> incidentService.getPageOfIncidentsByService("QRC", "sfkas", "fasf"));
    }

    @Test
    public void testSaveIncident() {
        String serviceName = "TestService";
        IncidentStatus incidentStatus = IncidentStatus.REPORTED;
        LocalDateTime incidentStartTime = LocalDateTime.now();
        LocalDateTime incidentEndTime = LocalDateTime.now().plusHours(1);

        incidentService.saveIncident(serviceName, incidentStatus, incidentStartTime, incidentEndTime);

        ArgumentCaptor<Incident> incidentArgumentCaptor = ArgumentCaptor.forClass(Incident.class);
        verify(incidentRepository).save(incidentArgumentCaptor.capture());
        Incident savedIncident = incidentArgumentCaptor.getValue();

        assertThat(savedIncident.getServiceName()).isEqualTo(serviceName);
        assertThat(savedIncident.getIncidentStatus()).isEqualTo(incidentStatus);
        assertThat(savedIncident.getIncidentStartTime()).isEqualTo(incidentStartTime);
        assertThat(savedIncident.getIncidentEndTime()).isEqualTo(incidentEndTime);
    }
}