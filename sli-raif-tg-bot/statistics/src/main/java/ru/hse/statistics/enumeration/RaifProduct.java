package ru.hse.statistics.enumeration;

public enum RaifProduct {
    /**
     * Система быстрых платежей.
     * */
    FPS("FPS"),
    /**
     * АДМ и инкассация.
     * */
    ACDC("acdc"),
    /**
     * Фискализация.
     * */
    FISCAL("fiscal"),
    /**
     * Торговый эквайринг.
     * */
    POS("POS"),
    /**
     * Корпоративные карты.
     * */
    DCC("dcc");

    final String name;
    RaifProduct(String product) {
        this.name = product;
    }
}
