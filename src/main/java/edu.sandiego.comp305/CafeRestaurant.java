package edu.sandiego.comp305;

import java.util.List;

public class CafeRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 7;

    private static final int CLOSE_HOUR = 22;

    private static final int DEFAULT_HAPPY_HOUR = 8;

    private static final int BASE_MAX_ORDERS = 390;

    public CafeRestaurant(){
        super("Cafe", RestaurantType.CAFE,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS,
                List.of(CafeMenuItem.values()));
    }

}
