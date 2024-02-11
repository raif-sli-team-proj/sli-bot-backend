package ru.hse.bot.configurtion;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.hse.statistics.repository")
public class AppConfiguration {
}
