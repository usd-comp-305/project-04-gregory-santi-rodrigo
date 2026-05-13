package edu.sandiego.comp305;

import java.util.List;

public class FineDiningRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 17;

    private static final int CLOSE_HOUR = 23;

    private static final int DEFAULT_HAPPY_HOUR = 17;

    private static final int BASE_MAX_ORDERS = 140;

    public FineDiningRestaurant(){
        super("Fine Dining", RestaurantType.FINE_DINING,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS,
                List.of(FineDiningMenuItem.values()));
    }
}
