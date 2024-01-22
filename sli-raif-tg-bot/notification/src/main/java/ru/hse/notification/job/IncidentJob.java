package ru.hse.notification.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IncidentJob implements Job {
    public void execute(JobExecutionContext arg0) {
        log.info("Incident job start.");
    }
}
