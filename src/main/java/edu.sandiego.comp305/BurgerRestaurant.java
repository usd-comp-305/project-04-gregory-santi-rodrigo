package edu.sandiego.comp305;

import java.util.List;

public class BurgerRestaurant extends AbstractRestaurant{

    private static final int OPEN_HOUR = 11;

    private static final int CLOSE_HOUR = 21;

    private static final int DEFAULT_HAPPY_HOUR = 15;

    private static final int BASE_MAX_ORDERS = 180;

    public BurgerRestaurant(){
        super("Burger Joint", RestaurantType.BURGER,
                OPEN_HOUR, CLOSE_HOUR, DEFAULT_HAPPY_HOUR, BASE_MAX_ORDERS,
                List.of(BurgerMenuItem.values()));
    }

}
