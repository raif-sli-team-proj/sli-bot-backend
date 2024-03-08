package ru.hse.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hse.api.dto.FPSStatusInfo;

@Component
@RequiredArgsConstructor
public class RaifClient {
    private final WebClient webClient;

    public FPSStatusInfo getFPSStatusInfo() {
        return webClient
                .get()
                .uri("/FPS")
                .retrieve()
                .bodyToMono(FPSStatusInfo.class)
                .block();
    }
}
