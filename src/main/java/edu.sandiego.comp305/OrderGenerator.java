package edu.sandiego.comp305;

import java.util.List;

public class OrderGenerator {
    private static final int MIN_ORDERS = 50;
    private static final int MAX_ORDERS = 100;

    private static final int DAY_START_HOUR = 8;
    private static final int DAY_END_HOUR = 22;

    private static final int LUNCH_RUSH_HOUR = 12;
    private static final int DINNER_RUSH_HOUR = 18;

    public List<Order> generateDailyOrders(Restaurant restaurant) {
        // TODO: implement weighted random distribution
        // weight[hour] = base + rushBonus(hour) + happyHourBonus(hour, restaurant)
        return List.of();
    }

    private int chooseTotalOrders() {
        // TODO: random int between MIN_ORDERS and MAX_ORDERS
        return 0;
    }

    private int chooseHour(Restaurant restaurant) {
        // TODO: weighted random pick from DAY_START_HOUR to DAY_END_HOUR
        // higher weight for LUNCH_RUSH_HOUR, DINNER_RUSH_HOUR, restaurant.getHappyHourStart()
        return 0;
    }

    private String chooseItem(Restaurant restaurant) {
        // TODO: randomly pick from restaurant's menu
        return "";
    }
}