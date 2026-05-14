package edu.sandiego.comp305;

public enum PizzaMenuItem implements MenuItem {
    PEPPERONI_SLICE("Pepperoni Slice", 3.99),
    PEPPERONI_WHOLE("Pepperoni Whole", 18.99),
    MARGHERITA_SLICE("Margherita Slice", 3.49),
    MARGHERITA_WHOLE("Margherita Whole", 16.99),
    CHEESE_SLICE("Cheese Slice", 2.99),
    CHEESE_WHOLE("Cheese Whole", 14.99);

    private final String name;

    private final double basePrice;

    PizzaMenuItem(final String name, final double basePrice) {
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
