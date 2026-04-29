package edu.sandiego.comp305;

public class PizzaRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 10;
    private static final int CLOSE_HOUR = 22;
    private static final int DEFAULT_HAPPY_HOUR = 17;
    private static final int BASE_MAX_ORDERS = 70;

    public PizzaRestaurant(){
        super("Pizza Place", RestaurantType.PIZZA,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS);
    }

    @Override
    public double processOrder(Order order){
        return processOrderBase(order);
    }
}
