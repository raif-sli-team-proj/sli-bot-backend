package ru.hse.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationConfig {

    @Value("${raiffeisen-status.base-url}")
    private String baseUrl;

    @Value("${web-client.connect-timeout}")
    private Integer connectTimeout;

    @Value("${web-client.response-timeout}")
    private Integer responseTimeout;

    @Value("${web-client.read-timeout}")
    private Integer readTimeout;

    @Value("${web-client.write-timeout}")
    private Integer writeTimeout;
}
