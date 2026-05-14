package edu.sandiego.comp305;

public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(final Order order) {
        return order.getItem().getBasePrice();
    }

}
