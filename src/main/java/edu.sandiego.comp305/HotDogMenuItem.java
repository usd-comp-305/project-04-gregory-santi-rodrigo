package edu.sandiego.comp305;

public enum HotDogMenuItem implements MenuItem {
    PLAIN_HOT_DOG("Plain Hot Dog", 3.99),
    CHILI_DOG("Chili Dog", 5.99),
    CONDIMENT_HOT_DOG("Condiment Hot Dog", 4.99);

    private final String name;

    private final double basePrice;

    HotDogMenuItem(final String name, final double basePrice) {
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
