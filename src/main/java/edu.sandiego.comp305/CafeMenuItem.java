package edu.sandiego.comp305;

public enum CafeMenuItem implements MenuItem {
    COFFEE("Coffee", 3.99),
    LATTE("Latte", 5.99),
    PASTRY("Pastry", 4.49);

    private final String name;

    private final double basePrice;

    CafeMenuItem(final String name, final double basePrice) {
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
