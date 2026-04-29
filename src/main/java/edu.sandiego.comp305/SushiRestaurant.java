package edu.sandiego.comp305;

public class SushiRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 11;
    private static final int CLOSE_HOUR = 21;
    private static final int DEFAULT_HAPPY_HOUR = 17;
    private static final int BASE_MAX_ORDERS = 60;

    public SushiRestaurant(){
        super("Sushi Bar", RestaurantType.SUSHI,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS);
    }

    @Override
    public double processOrder(Order order){
        return processOrderBase(order);
    }
}
