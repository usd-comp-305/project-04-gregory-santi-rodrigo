package edu.sandiego.comp305;

public enum FineDiningMenuItem implements MenuItem {
    STEAK("Steak", 45.99),
    PASTA("Pasta", 24.99),
    CAVIAR("Caviar", 89.99);

    private final String name;
    private final double basePrice;

    FineDiningMenuItem(String name, double basePrice) {
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
