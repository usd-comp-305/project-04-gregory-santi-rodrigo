package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private double netWorth;
    private final List<Restaurant> restaurants;
    private final double goalNetWorth;
    private static final double UPGRADE_COST = 1000;
    private static final double RAW_MATERIAL_RATE = 0.30;
    private static final double HAPPY_HOUR_DISCOUNT = 0.20;
    private static final double DAILY_EXPENSE = 500;
    private static final double UPGRADED_DAILY_EXPENSE = 1000;

    public Owner(double startingNetWorth, double goalNetWorth, List<Restaurant> restaurants) {
        this.netWorth = startingNetWorth;
        this.goalNetWorth = goalNetWorth;
        this.restaurants = new ArrayList<>(restaurants);
    }

    public double getNetWorth()               { return netWorth; }
    public double getGoalNetWorth()           { return goalNetWorth; }
    public List<Restaurant> getRestaurants()  { return restaurants; }

    public void applyDailyReport(DailyReport report) {
        for (RestaurantReport rr : report.getRestaurantReports()) {
            double regularRevenue = rr.getRegularRevenue();
            double happyHourRevenue = rr.getHappyHourRevenue();
            double regularProfit = regularRevenue * (1 - RAW_MATERIAL_RATE);
            double originalHHPrice = happyHourRevenue / (1 - HAPPY_HOUR_DISCOUNT);
            double happyHourProfit = happyHourRevenue - (originalHHPrice * RAW_MATERIAL_RATE);
            double expense = rr.isUpgraded() ? UPGRADED_DAILY_EXPENSE : DAILY_EXPENSE;
            double dailyProfit = regularProfit + happyHourProfit - expense;
            netWorth += dailyProfit;
        }
    }

    public void upgrade(Restaurant restaurant) {
        if (restaurant.isUpgraded()) {
            System.out.println(restaurant.getName() + " has already been upgraded.");
        } else if (netWorth < UPGRADE_COST) {
            System.out.println("You can't afford the upgrade. It costs $" + (int) UPGRADE_COST + ".");
        } else {
            netWorth -= UPGRADE_COST;
            restaurant.upgrade();
            System.out.println(restaurant.getName() + " has been upgraded! Max orders increased by 50%.");
        }
    }

    public void shutdownRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public void changeHappyHour(Restaurant restaurant, int newHour) {
        if (!restaurant.isOpen(newHour)) {
            System.out.println("Invalid hour — " + restaurant.getName() + " is not open at " + newHour + ":00.");
            return;
        }
        restaurant.setHappyHourStart(newHour);
        System.out.println("Happy hour updated to " + newHour + ":00.");
    }

    public boolean hasWon()  { return netWorth >= goalNetWorth; }
    public boolean hasLost() { return netWorth <= 0; }
}