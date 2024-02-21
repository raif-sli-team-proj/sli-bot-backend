package ru.hse.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
        "ru.hse.api",
        "ru.hse.statistics",
        "ru.hse.core",
        "ru.hse.bot",
        "ru.hse.notification"
})
@EntityScan(value = {
        "ru.hse.statistics.repository.entity",
        "ru.hse.core.entity"
})
public class BotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

}
