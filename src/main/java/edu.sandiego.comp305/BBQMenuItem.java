package edu.sandiego.comp305;

public enum BBQMenuItem implements MenuItem {
    RIBS("Ribs", 22.99),
    BRISKET("Brisket", 18.99),
    PULLED_PORK("Pulled Pork", 16.99);

    private final String name;

    private final double basePrice;

    BBQMenuItem(final String name, final double basePrice) {
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
