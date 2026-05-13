package edu.sandiego.comp305;

public class RestaurantReport {

    private final String restaurantName;

    private final double regularRevenue;

    private final double happyHourRevenue;

    private final int peakHour;

    private final boolean upgraded;

    private final double regularProfit;

    private final double happyHourProfit;

    private final double totalProfit;



    public RestaurantReport(final String restaurantName,
                            final double regularRevenue,
                            final double happyHourRevenue,
                            final int peakHour,
                            final boolean upgraded,
                            final double regularProfit,
                            final double happyHourProfit,
                            final double totalProfit) {
        this.restaurantName = restaurantName;
        this.regularRevenue = regularRevenue;
        this.happyHourRevenue = happyHourRevenue;
        this.peakHour = peakHour;
        this.upgraded = upgraded;
        this.regularProfit = regularProfit;
        this.happyHourProfit = happyHourProfit;
        this.totalProfit = totalProfit;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public double getRegularRevenue() {
        return regularRevenue;
    }

    public double getHappyHourRevenue() {
        return happyHourRevenue;
    }

    public double getTotalRevenue() {
        return regularRevenue + happyHourRevenue;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public double getRegularProfit() {
        return regularProfit;
    }

    public double getHappyHourProfit() {
        return happyHourProfit;
    }

    public double getTotalProfit() {
        return totalProfit;
    }


    @Override
    public String toString() {
        final String upgradeLabel;

        if (upgraded) {
            upgradeLabel = " [UPGRADED]";
        } else {
            upgradeLabel = "";
        }

        return String.format(
                "  %-20s | Regular: $%7.2f | " +
                        "Happy Hour: $%7.2f | " +
                        "Total: $%7.2f | Peak Hour: %2d:00%s",
                restaurantName,
                regularProfit,
                happyHourProfit,
                totalProfit,
                peakHour,
                upgradeLabel);
    }
}
