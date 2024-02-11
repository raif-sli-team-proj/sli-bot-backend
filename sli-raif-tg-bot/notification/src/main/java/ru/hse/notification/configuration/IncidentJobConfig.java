package ru.hse.notification.configuration;

import lombok.AllArgsConstructor;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import ru.hse.notification.factory.JobDetailFactory;
import ru.hse.notification.factory.SchedulerTriggerFactory;
import ru.hse.notification.job.IncidentJob;

@Configuration
@AllArgsConstructor
public class IncidentJobConfig {

    private final SchedulerTriggerFactory triggerFactory;
    private final JobDetailFactory jobDetailFactory;

    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * ? * * *";

    @Bean(name = "incidentJobDetail")
    public JobDetailFactoryBean jobMemberStats() {
        return jobDetailFactory.createJobDetail(IncidentJob.class, "Incident Job");
    }

    @Bean(name = "incidentJobTrigger")
    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("incidentJobDetail") JobDetail jobDetail) {
        return triggerFactory.createTrigger(jobDetail, 5 * 60 * 1000, "Incident job Trigger");
    }
}
