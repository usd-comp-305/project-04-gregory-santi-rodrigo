package edu.sandiego.comp305;

public enum SushiMenuItem implements MenuItem {
    SALMON_ROLL("Salmon Roll", 12.99),
    TUNA_ROLL("Tuna Roll", 13.99),
    CHEFS_NIGIRI_PLATTER("Chef's Nigiri Platter", 24.99),
    HAMACHI_ROLL("Hamachi Roll", 14.99);

    private final String name;
    private final double basePrice;

    SushiMenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override public String getName() {
        return name;
    }
    @Override public double getBasePrice() {
        return basePrice;
    }
}
