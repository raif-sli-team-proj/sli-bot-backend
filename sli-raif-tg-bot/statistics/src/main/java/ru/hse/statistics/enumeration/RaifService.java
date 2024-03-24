package ru.hse.statistics.enumeration;

import java.util.stream.Stream;

public enum RaifService {
    QRC("FPS", "QRC"),
    PAYMENTS_PROCESSING("acdc", "payments_processing"),
    RECEIPTS_SERVICE("fiscal", "receipts-service"),
    PAYMENTS("POS", "Payments"),
    OPERATIONS("dcc", "operations");

    public final String product;
    public final String service;

    RaifService(String product, String service) {
        this.product = product;
        this.service = service;
    }

    public static RaifService of(String product, String service) {
        return Stream.of(values())
                .filter(s -> s.product.equals(product) && s.service.equals(service))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Failed to find Raif service of product" +
                        " %s with service name %s", product, service)));
    }
}
