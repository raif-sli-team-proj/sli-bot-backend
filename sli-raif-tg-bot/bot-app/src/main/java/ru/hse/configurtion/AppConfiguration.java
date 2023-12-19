package ru.hse.configurtion;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hse.config.RaifConfig;
import ru.hse.config.WebclientConfig;

@Configuration
@Import({RaifConfig.class, WebclientConfig.class})
public class AppConfiguration {
}
