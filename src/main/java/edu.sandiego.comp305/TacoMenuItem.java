package edu.sandiego.comp305;

public enum TacoMenuItem implements MenuItem {
    STEAK_TACO("Steak Taco", 4.99),
    CHICKEN_TACO("Chicken Taco", 3.99),
    CHICKEN_BURRITO("Chicken Burrito", 9.99),
    STEAK_BURRITO("Steak Burrito", 11.99);

    private final String name;
    private final double basePrice;

    TacoMenuItem(String name, double basePrice) {
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
