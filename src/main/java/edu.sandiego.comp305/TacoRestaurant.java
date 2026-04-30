package edu.sandiego.comp305;

public class TacoRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 8;
    private static final int CLOSE_HOUR = 23;
    private static final int DEFAULT_HAPPY_HOUR = 16;
    private static final int BASE_MAX_ORDERS = 80;

    public TacoRestaurant(){
        super("Taco Stand", RestaurantType.TACO,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS);
    }

    @Override
    public double processOrder(Order order){
        return processOrderBase(order);
    }
}
