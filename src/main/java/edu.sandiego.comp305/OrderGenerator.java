package edu.sandiego.comp305;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    private static final int MIN_ORDERS = 50;

    private static final int MAX_ORDERS = 100;

    private static final int LUNCH_RUSH_HOUR = 12;

    private static final int DINNER_RUSH_HOUR = 18;

    private static final int BASE_WEIGHT = 5;

    private static final int RUSH_BONUS = 10;

    private static final int HAPPY_HOUR_BONUS = 8;

    private final Random random;

    public OrderGenerator() {
        this.random = new Random();
    }

    public OrderGenerator(Random random) {
        this.random = random;
    }

    /*
        public List<Order> generateDailyOrders(Restaurant restaurant) {
            int totalOrders = chooseTotalOrders();
            List<Order> orders = new ArrayList<>();

            for (int i = 0; i < totalOrders; i++) {
                int hour = chooseHour(restaurant);
                MenuItem item = chooseItem(restaurant);
                orders.add(new Order(item, hour, restaurant.getType()));
            }

            return orders;
        }

        private int chooseTotalOrders() {
            return MIN_ORDERS + random.nextInt(MAX_ORDERS - MIN_ORDERS + 1);
        }

        private int chooseHour(Restaurant restaurant) {
            List<Integer> openHours = getOpenHours(restaurant);
            int[] weights = buildWeights(openHours, restaurant);
            return weightedPick(openHours, weights);
        }

        private List<Integer> getOpenHours(Restaurant restaurant) {
            List<Integer> openHours = new ArrayList<>();
            for (int hour = 0; hour < 24; hour++) {
                if (restaurant.isOpen(hour)) {
                    openHours.add(hour);
                }
            }
            return openHours;
        }

        private int[] buildWeights(List<Integer> openHours, Restaurant restaurant) {
            int[] weights = new int[openHours.size()];
            for (int i = 0; i < openHours.size(); i++) {
                int hour = openHours.get(i);
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

        private int weightedPick(List<Integer> hours, int[] weights) {
            int totalWeight = 0;
            for (int w : weights) {
                totalWeight += w;
            }

            int roll = random.nextInt(totalWeight);
            int cumulative = 0;

            for (int i = 0; i < hours.size(); i++) {
                cumulative += weights[i];
                if (roll < cumulative) {
                    return hours.get(i);
                }
            }

            return hours.get(hours.size() - 1);
        }

        private MenuItem chooseItem(Restaurant restaurant) {
            List<MenuItem> menu = restaurant.getMenu();
            return menu.get(random.nextInt(menu.size()));
        }
    }
    */
    public List<Order> generateDailyOrders(Restaurant restaurant) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            orders.add(new Order(restaurant.getMenu().get(0), 9, restaurant.getType()));
        }
        return orders;
    }
}