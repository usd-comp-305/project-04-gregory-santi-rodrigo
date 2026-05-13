package edu.sandiego.comp305;

public class Order {

    private final MenuItem item;

    private final int hour;

    private final RestaurantType restaurantType;

    public Order(final MenuItem item,
                 final int hour,
                 final RestaurantType restaurantType) {
        this.item = item;
        this.hour = hour;
        this.restaurantType = restaurantType;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getHour() {
        return hour;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }
}
