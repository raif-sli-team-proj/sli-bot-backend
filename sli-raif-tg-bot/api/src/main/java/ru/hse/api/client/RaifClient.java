package ru.hse.api.client;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hse.api.dto.FPSStatusInfo;
import ru.hse.api.dto.raif.RaifProductDto;

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

    public List<RaifProductDto> getAllProductsInfo() {
        return webClient
                .get()
                .retrieve()
                .bodyToFlux(RaifProductDto.class)
                .toStream().toList();
    }
}
