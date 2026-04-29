package edu.sandiego.comp305;

public enum BurgerMenuItem implements MenuItem {
    CHEESEBURGER("Cheeseburger", 8.99),
    DOUBLE_CHEESEBURGER("Double Cheeseburger", 11.99),
    TRIPLE_CHEESEBURGER("Triple Cheeseburger", 14.99);

    private final String name;
    private final double basePrice;

    BurgerMenuItem(String name, double basePrice) {
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
