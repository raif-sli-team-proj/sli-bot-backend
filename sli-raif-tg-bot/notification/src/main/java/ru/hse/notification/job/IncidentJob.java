package ru.hse.notification.job;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
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
        Status status = statusService.getServiceStatus("QRC");
        if (status == Status.FAIL) {
            log.info("QRC service failed.");
            Set<String> chats = subscriptionService.getAllSubscriptions().stream()
                    .map(Subscription::getChatId)
                    .collect(Collectors.toSet());
            bot.sendMessages(chats, "❌❌❌ QRC service failed.");
            // stub
            incidentService.saveIncident("QRC", IncidentStatus.REPORTED, LocalDateTime.now(), null);
        }
        statusService.addStatus(status);
        log.info("Incident job end.");
    }
}
