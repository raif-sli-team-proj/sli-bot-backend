package ru.hse.notification.job;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import ru.hse.statistics.enumeration.RaifService;
import ru.hse.statistics.model.IncidentStatus;
import ru.hse.statistics.service.IncidentService;
import ru.hse.notification.telegram.TgBot;
import ru.hse.notification.SubscriptionService;
import ru.hse.notification.repository.entity.Subscription;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.service.StatusService;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncidentJob implements Job {
    private final StatusService statusService;
    private final TgBot bot;
    private final IncidentService incidentService;
    private final SubscriptionService subscriptionService;

    public void execute(JobExecutionContext arg0) {
        log.info("Incident job start.");

        Map<RaifService, Status> serviceToStatus = statusService.getAllServiceStatuses();

        Map<RaifService, Status> failedServices = serviceToStatus.entrySet().stream()
                .filter(e -> e.getValue() == Status.FAIL)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Сохраняем статусы.
        statusService.addStatuses(serviceToStatus);

        if (!failedServices.isEmpty()) {
            Set<String> chats = subscriptionService.getAllSubscriptions().stream()
                    .map(Subscription::getChatId)
                    .collect(Collectors.toSet());
            // Отправляем уведомления об упавших сервисах, создаем инциденты.
            failedServices.forEach((key, value) -> {
                log.info(String.format("%s service failed.", key.service));
                bot.sendMessages(chats, String.format("❌ сервис «%s» упал.", key.name));
                incidentService.saveIncident(key.service, IncidentStatus.REPORTED, LocalDateTime.now(), null);
            });
        }
        log.info("Incident job end.");
    }
}
