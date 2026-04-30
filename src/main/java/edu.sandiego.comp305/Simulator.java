package edu.sandiego.comp305;

import java.util.List;

public class Simulator {
    private final List<Restaurant> restaurants;
    private final OrderGenerator orderGenerator;
    private int currentDay;

    public Simulator(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        this.orderGenerator = new OrderGenerator();
        this.currentDay = 1;
    }

    public DailyReport runDay() {
        // TODO: for each restaurant, reset day, generate orders, process each order,
        // track peak hour, then call generateReport()
        currentDay++;
        return null;
    }

    private int computePeakHour(List<Order> orders) {
        // TODO: find the hour with the most orders
        return 0;
    }

    public int getCurrentDay() { return currentDay; }
}