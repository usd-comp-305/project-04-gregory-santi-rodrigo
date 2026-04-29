package edu.sandiego.comp305;

public class IceCreamRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 11;
    private static final int CLOSE_HOUR = 21;
    private static final int DEFAULT_HAPPY_HOUR = 15;
    private static final int BASE_MAX_ORDERS = 70;

    public IceCreamRestaurant(){
        super("Ice Cream Shop", RestaurantType.ICE_CREAM_SHOP,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS);
    }

    @Override
    public double processOrder(Order order){
        return processOrderBase(order);
    }
}
