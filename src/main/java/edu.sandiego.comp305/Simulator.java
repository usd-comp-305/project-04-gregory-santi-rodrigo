package edu.sandiego.comp305;

import java.util.ArrayList;
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

    public Simulator(List<Restaurant> restaurants, OrderGenerator orderGenerator){
        this.restaurants = restaurants;
        this.orderGenerator = orderGenerator;
        this.currentDay = 1;
    }

    public DailyReport runDay() {
        List<RestaurantReport> reports = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            restaurant.resetDay();

            List<Order> orders = orderGenerator.generateDailyOrders(restaurant);
            int allowedOrders = Math.min(orders.size(), restaurant.getMaxOrdersPerDay());

            for (int orderCount = 0; orderCount < allowedOrders; orderCount++) {
                restaurant.processOrder(orders.get(orderCount));
            }

            int peakHour = computePeakHour(orders.subList(0, allowedOrders));
            reports.add(restaurant.generateReport(peakHour));
        }

        DailyReport dailyReport = new DailyReport(currentDay, reports);
        currentDay++;
        return dailyReport;
    }

    private int computePeakHour(List<Order> orders) {
        int[] hourCounts = new int[24];
        for (Order order : orders) {
            hourCounts[order.getHour()]++;
        }

        int peakHour = 0;
        for (int hour = 1; hour < 24; hour++) {
            if (hourCounts[hour] > hourCounts[peakHour]) {
                peakHour = hour;
            }
        }

        return peakHour;
    }

    public int getCurrentDay() { return currentDay; }
    public List<Restaurant> getRestaurants() {return restaurants;}
}