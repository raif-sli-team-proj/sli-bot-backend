package ru.hse.notification.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class IncidentJob implements Job {
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("This is a quartz job!");
    }
}
