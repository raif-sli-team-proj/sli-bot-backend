package ru.hse.bot.configurtion;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hse.api.config.RaifConfig;
import ru.hse.api.config.WebclientConfig;

@Configuration
@Import({RaifConfig.class, WebclientConfig.class})
public class AppConfiguration {
}
