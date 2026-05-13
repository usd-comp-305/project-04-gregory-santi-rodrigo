package edu.sandiego.comp305;

import java.util.List;

public class JuiceRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 8;

    private static final int CLOSE_HOUR = 19;

    private static final int DEFAULT_HAPPY_HOUR = 8;

    private static final int BASE_MAX_ORDERS = 160;

    public JuiceRestaurant(){
        super("Juice Bar", RestaurantType.JUICE_BAR,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS,
                List.of(JuiceBarMenuItem.values()));
    }

}
