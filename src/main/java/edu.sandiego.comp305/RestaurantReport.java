package edu.sandiego.comp305;

public class RestaurantReport {
    private final String restaurantName;
    private final double regularRevenue;
    private final double happyHourRevenue;
    private final int peakHour;

    public RestaurantReport(String restaurantName, double regularRevenue,
                            double happyHourRevenue, int peakHour) {
        this.restaurantName = restaurantName;
        this.regularRevenue = regularRevenue;
        this.happyHourRevenue = happyHourRevenue;
        this.peakHour = peakHour;
    }

    public String getRestaurantName()   { return restaurantName; }
    public double getRegularRevenue()   { return regularRevenue; }
    public double getHappyHourRevenue() { return happyHourRevenue; }
    public double getTotalRevenue()     { return regularRevenue + happyHourRevenue; }
    public int getPeakHour()            { return peakHour; }

    @Override
    public String toString() {
        return String.format("  %-20s | Regular: $%6.2f | Happy Hour: $%6.2f | Total: $%6.2f | Peak Hour: %2d:00",
                restaurantName, regularRevenue, happyHourRevenue, getTotalRevenue(), peakHour);
    }
}