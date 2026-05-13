package edu.sandiego.comp305;

import java.util.List;

public class BBQRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 11;

    private static final int CLOSE_HOUR = 21;

    private static final int DEFAULT_HAPPY_HOUR = 17;

    private static final int BASE_MAX_ORDERS = 150;

    public BBQRestaurant(){
        super("BBQ Shack", RestaurantType.BBQ,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS,
                List.of(BBQMenuItem.values()));
    }
}
