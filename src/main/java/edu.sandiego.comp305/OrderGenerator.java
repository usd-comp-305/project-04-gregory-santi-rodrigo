package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {

    private static final int MIN_ORDERS = 1000;

    private static final int MAX_ORDERS = 2000;

    private static final int BASE_WEIGHT = 5;

    private static final int HAPPY_HOUR_BONUS = 8;

    private static final int LUNCH_RUSH_HOUR = 12;

    private static final int DINNER_RUSH_HOUR = 18;

    private static final int RUSH_BONUS = 10;

    private final Random random;

    public OrderGenerator() {
        this.random = new Random();
    }

    public OrderGenerator(final Random random) {
        this.random = new Random(random.nextLong());
    }

    public List<Order> generateDailyOrders(final Restaurant restaurant) {
        final int totalOrders = MIN_ORDERS + random.nextInt(
                MAX_ORDERS - MIN_ORDERS + 1);
        final List<Order> orders = new ArrayList<>();
        for (int i = 0; i < totalOrders; i++) {
            final int hour = chooseHour(restaurant);
            final MenuItem item = chooseItem(restaurant);
            orders.add(new Order(item, hour, restaurant.getType()));
        }
        return orders;
    }

    private int chooseHour(final Restaurant restaurant) {
        final List<Integer> openHours = getOpenHours(restaurant);
        final int[] weights = buildWeights(openHours, restaurant);
        return weightedPick(openHours, weights);
    }

    private int weightedPick(final List<Integer> hours,
                             final int[] weights) {
        int total = 0;
        for (int w : weights) {
            total += w;
        }
        final int roll = random.nextInt(total);
        int cumulative = 0;
        for (int i = 0; i < hours.size(); i++) {
            cumulative += weights[i];
            if (roll < cumulative) {
                return hours.get(i);
            }
        }
        return hours.get(hours.size() - 1);
    }

    private int[] buildWeights(final List<Integer> openHours,
                               final Restaurant restaurant) {
        final int[] weights = new int[openHours.size()];
        for (int i = 0; i < openHours.size(); i++) {
            final int hour = openHours.get(i);
            weights[i] = BASE_WEIGHT;
            if (hour == LUNCH_RUSH_HOUR || hour == DINNER_RUSH_HOUR) {
                weights[i] += RUSH_BONUS;
            }
            if (restaurant.isHappyHour(hour)) {
                weights[i] += HAPPY_HOUR_BONUS;
            }
        }
        return weights;
    }

    private List<Integer> getOpenHours(final Restaurant restaurant) {
        final List<Integer> openHours = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            if (restaurant.isOpen(hour)) {
                openHours.add(hour);
            }
        }
        return openHours;
    }


    private MenuItem chooseItem(final Restaurant restaurant) {
        final List<MenuItem> menu = restaurant.getMenu();
        return menu.get(random.nextInt(menu.size()));
    }
}
