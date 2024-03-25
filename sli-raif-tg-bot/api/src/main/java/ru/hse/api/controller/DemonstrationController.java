package ru.hse.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.api.dto.DemoIncidentResponse;
import ru.hse.notification.SubscriptionService;
import ru.hse.notification.repository.entity.Subscription;
import ru.hse.notification.telegram.TgBot;
import ru.hse.statistics.enumeration.RaifService;
import ru.hse.statistics.model.IncidentStatus;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.service.IncidentService;
import ru.hse.statistics.service.StatusService;

@RestController
@RequestMapping(path = "api/v1/demo/incident")
@RequiredArgsConstructor
public class DemonstrationController {

    private final IncidentService incidentService;
    private final StatusService statusService;
    private final SubscriptionService subscriptionService;

    private final TgBot bot;


    // TODO remove or use only for testing after demonstration 27.03.2024.
    @PostMapping
    public DemoIncidentResponse createDemoIncident() {
        List<RaifService> services = List.of(RaifService.values());
        Random random = new Random();
        int randomIndex = random.nextInt(services.size());
        RaifService rndService = services.get(randomIndex);

        statusService.addStatus(rndService, Status.FAIL);
        Set<String> chats = subscriptionService.getAllSubscriptions().stream()
                .map(Subscription::getChatId)
                .collect(Collectors.toSet());
        bot.sendMessages(chats, String.format("❌❌❌ %s service failed.", rndService.service));
        incidentService.saveIncident(
                rndService.service,
                IncidentStatus.REPORTED,
                LocalDateTime.now(),
                null);
        return new DemoIncidentResponse(rndService.product, rndService.service);
    }
}
