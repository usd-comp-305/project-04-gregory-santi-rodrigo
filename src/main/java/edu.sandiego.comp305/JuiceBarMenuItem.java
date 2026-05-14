package edu.sandiego.comp305;

public enum JuiceBarMenuItem implements MenuItem {
    ORANGE_JUICE("Orange Juice", 4.99),
    STRAWBERRY_SMOOTHIE("Strawberry Smoothie", 7.99),
    ACAI_BOWL("Acai Bowl", 12.99),
    GINGER_SHOT("Ginger Shot", 3.99);

    private final String name;

    private final double basePrice;

    JuiceBarMenuItem(final String name, final double basePrice) {
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
