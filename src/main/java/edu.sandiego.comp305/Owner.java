package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Owner {

    private static final double UPGRADE_COST = 1000;

    private static final double RAW_MATERIAL_RATE = 0.30;

    private static final double HAPPY_HOUR_DISCOUNT = 0.20;

    private static final double DAILY_EXPENSE = 500;

    private static final double UPGRADED_DAILY_EXPENSE = 1000;

    private double netWorth;

    private final List<Restaurant> restaurants;

    private final double goalNetWorth;

    public Owner(final double startingNetWorth,
                 final double goalNetWorth,
                 final List<Restaurant> restaurants) {
        this.netWorth = startingNetWorth;
        this.goalNetWorth = goalNetWorth;
        this.restaurants = new ArrayList<>(restaurants);
    }

    public double getNetWorth() {
        return netWorth;
    }

    public double getGoalNetWorth() {
        return goalNetWorth;
    }

    public List<Restaurant> getRestaurants()  {
        return restaurants;
    }

    public DailyReport applyDailyReport(final DailyReport report) {
        final List<RestaurantReport> updatedReports = new ArrayList<>();
        for (RestaurantReport rr : report.getRestaurantReports()) {
            final double regularRevenue = rr.getRegularRevenue();
            final double happyHourRevenue = rr.getHappyHourRevenue();
            final double regularProfit = regularRevenue *
                    (1 - RAW_MATERIAL_RATE);
            final double originalHHPrice = happyHourRevenue /
                    (1 - HAPPY_HOUR_DISCOUNT);
            final double happyHourProfit = happyHourRevenue -
                    (originalHHPrice * RAW_MATERIAL_RATE);
            final double expense;
            if (rr.isUpgraded()) {
                expense = UPGRADED_DAILY_EXPENSE;
            } else {
                expense = DAILY_EXPENSE;
            }
            final double totalProfit = regularProfit +
                    happyHourProfit - expense;

            netWorth += totalProfit;

            updatedReports.add(new RestaurantReport(
                    rr.getRestaurantName(), regularRevenue,
                    happyHourRevenue, rr.getPeakHour(),
                    rr.isUpgraded(), regularProfit, happyHourProfit,
                    totalProfit
            ));
        }
        return new DailyReport(report.getDayNumber(), updatedReports);
    }

    public void upgrade(final Restaurant restaurant) {
        if (restaurant.isUpgraded()) {
            System.out.println(restaurant.getName() +
                    " has already been upgraded.");
        } else if (netWorth < UPGRADE_COST) {
            System.out.println("You can't afford the upgrade. " +
                    "It costs $"
                    + (int) UPGRADE_COST + ".");
        } else {
            netWorth -= UPGRADE_COST;
            restaurant.upgrade();
            System.out.println(restaurant.getName() +
                    " has been upgraded! Max orders increased by 50%.");
        }
    }

    public void shutdownRestaurant(final Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public void changeHappyHour(final Restaurant restaurant,
                                final int newHour) {
        if (!restaurant.isOpen(newHour)) {
            System.out.println("Invalid hour — " + restaurant.getName() +
                    " is not open at " + newHour + ":00.");
            return;
        }
        restaurant.setHappyHourStart(newHour);
        System.out.println("Happy hour updated to " + newHour + ":00.");
    }

    public boolean hasWon()  {
        return netWorth >= goalNetWorth;
    }

    public boolean hasLost() {
        return netWorth <= 0;
    }
}
