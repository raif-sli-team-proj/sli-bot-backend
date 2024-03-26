package ru.hse.statistics.enumeration;

import java.util.stream.Stream;

public enum RaifService {
    QRC("FPS", "QRC", "Регистрация QR"),
    PAYMENTS_PROCESSING("acdc", "payments_processing", "Внесение ДС"),
    RECEIPTS_SERVICE("fiscal", "receipts-service", "Управление чеками"),
    PAYMENTS("POS", "Payments", "Проведение платежей"),
    OPERATIONS("dcc", "operations", "Получение списка операций");

    public final String product;
    public final String service;
    public final String name;

    RaifService(String product, String service, String name) {
        this.product = product;
        this.service = service;
        this.name = name;
    }

    public static RaifService of(String product, String service) {
        return Stream.of(values())
                .filter(s -> s.product.equals(product) && s.service.equals(service))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Failed to find Raif service of product" +
                        " %s with service name %s", product, service)));
    }
}
