package edu.sandiego.comp305;

public interface PricingStrategy {

    public abstract double calculatePrice(final Order order);

}
