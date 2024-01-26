package ru.hse.notification.configuration;


import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import ru.hse.notification.factory.AutowiringSpringBeanJobFactory;

@Configuration
public class SchedulerConfiguration {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public Scheduler schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory, @Qualifier(
            "incidentJobTrigger") Trigger incidentJobTrigger) throws Exception {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setJobFactory(jobFactory);

        schedulerFactory.setQuartzProperties(quartzProperties());
        schedulerFactory.afterPropertiesSet();

        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.setJobFactory(jobFactory);
//        schedulerFactory.setTriggers(incidentJobTrigger);
        scheduler.scheduleJob((JobDetail) incidentJobTrigger.getJobDataMap().get("jobDetail"), incidentJobTrigger);

        scheduler.start();
        return scheduler;
    }

}
