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

    public final String productName;
    RaifProduct(String product) {
        this.productName = product;
    }
}
