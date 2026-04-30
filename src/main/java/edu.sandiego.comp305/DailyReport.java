package edu.sandiego.comp305;

import java.util.List;

public class DailyReport {
    private final int dayNumber;
    private final List<RestaurantReport> restaurantReports;
    private final double totalNetChange;

    public DailyReport(int dayNumber, List<RestaurantReport> restaurantReports) {
        this.dayNumber = dayNumber;
        this.restaurantReports = restaurantReports;
        this.totalNetChange = restaurantReports.stream()
                .mapToDouble(RestaurantReport::getTotalRevenue)
                .sum();
    }

    public int getDayNumber()                           { return dayNumber; }
    public List<RestaurantReport> getRestaurantReports() { return restaurantReports; }
    public double getTotalNetChange()                   { return totalNetChange; }
}