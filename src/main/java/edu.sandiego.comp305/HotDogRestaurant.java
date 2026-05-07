package edu.sandiego.comp305;

public class HotDogRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 20;

    private static final int CLOSE_HOUR = 11;

    private static final int DEFAULT_HAPPY_HOUR = 22;

    private static final int BASE_MAX_ORDERS = 90;

    public HotDogRestaurant(){
        super("Hot Dog Stand", RestaurantType.HOT_DOG,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS);
    }

    @Override
    public double processOrder(Order order){
        return processOrderBase(order);
    }
}
