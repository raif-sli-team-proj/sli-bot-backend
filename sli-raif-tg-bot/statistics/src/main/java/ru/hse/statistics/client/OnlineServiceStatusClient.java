package ru.hse.statistics.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hse.statistics.enumeration.RaifProduct;
import ru.hse.statistics.model.ProductOnlineStatusesResponse;

@Component
@RequiredArgsConstructor
public class OnlineServiceStatusClient {
    private final WebClient webClient;

    public ProductOnlineStatusesResponse getOnlineStatus(RaifProduct product) {
        return webClient
                .get()
                .uri(String.format("/%s/online", product.productName))
                .retrieve()
                .bodyToMono(ProductOnlineStatusesResponse.class)
                .block();
    }

}
