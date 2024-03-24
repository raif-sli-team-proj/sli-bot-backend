package ru.hse.statistics.enumeration;

public enum RaifService {
    QRC("FPS", "QRC"),
    PAYMENTS_PROCESSING("acdc", "payments_processing"),
    RECEIPTS_SERVICE("fiscal", "receipts-service"),
    PAYMENTS("POS", "Payments"),
    OPERATIONS("dcc", "operations");

    final String product;
    final String service;

    RaifService(String product, String service) {
        this.product = product;
        this.service = service;
    }
}
