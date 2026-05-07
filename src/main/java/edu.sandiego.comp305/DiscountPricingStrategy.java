package edu.sandiego.comp305;

public class DiscountPricingStrategy implements PricingStrategy {
    private static final double DISCOUNT_RATE = 0.20;

    @Override
    public double calculatePrice(Order order) {
        return order.getItem().getBasePrice() * (1 - DISCOUNT_RATE);
    }
}
