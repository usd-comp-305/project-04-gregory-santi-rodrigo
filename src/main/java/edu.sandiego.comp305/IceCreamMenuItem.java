package edu.sandiego.comp305;

public enum IceCreamMenuItem implements MenuItem {
    CONE("Cone", 3.99),
    SUNDAE("Sundae", 6.99),
    SHAKE("Shake", 7.99);

    private final String name;

    private final double basePrice;

    IceCreamMenuItem(final String name, final double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }
}
