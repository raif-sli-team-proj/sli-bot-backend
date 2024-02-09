package ru.hse.notification.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import ru.hse.statistics.service.ServiceStatusService;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncidentJob implements Job {

    private final ServiceStatusService serviceStatusService;

    public void execute(JobExecutionContext arg0) {
        log.info("Incident job start.");
        serviceStatusService.addCurrentStatuses();
    }
}
