package edu.sandiego.comp305;

import java.util.List;

public class DailyReport {

    private final int dayNumber;

    private final List<RestaurantReport> restaurantReports;

    private final double totalNetChange;

    public DailyReport(final int dayNumber,
                       final List<RestaurantReport> restaurantReports) {
        this.dayNumber = dayNumber;
        this.restaurantReports = List.copyOf(restaurantReports);
        this.totalNetChange = restaurantReports.stream()
                .mapToDouble(RestaurantReport::getTotalRevenue)
                .sum();
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public List<RestaurantReport> getRestaurantReports() {
        return List.copyOf(restaurantReports);
    }

    public double getTotalNetChange() {
        return totalNetChange;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== Day %d ===%n", dayNumber));
        for (RestaurantReport report : restaurantReports) {
            sb.append(report).append("\n");
        }
        sb.append(String.format("  Total Revenue: $%.2f", totalNetChange));
        return sb.toString();
    }
}
