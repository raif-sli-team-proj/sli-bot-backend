package ru.hse.bot.configurtion;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = {
        "ru.hse.statistics.repository",
        "ru.hse.notification.repository",
        "ru.hse.core.repository"
})
public class AppConfiguration {
}
