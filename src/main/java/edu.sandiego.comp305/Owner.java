package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private double netWorth;
    private final List<Restaurant> restaurants;
    private final double goalNetWorth;
    private static final double UPGRADE_COST = 1000;

    public Owner(double startingNetWorth, double goalNetWorth, List<Restaurant> restaurants) {
        this.netWorth = startingNetWorth;
        this.goalNetWorth = goalNetWorth;
        this.restaurants = new ArrayList<>(restaurants);
    }

    public double getNetWorth()               { return netWorth; }
    public double getGoalNetWorth()           { return goalNetWorth; }
    public List<Restaurant> getRestaurants()  { return restaurants; }

    public void applyDailyReport(DailyReport report) {
        netWorth += report.getTotalNetChange();
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

    public void applyProfit(double profit) {
        netWorth += profit;
    }

    public void shutdownRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }

    public void changeHappyHour(Restaurant restaurant, int newHour) {
        restaurant.setHappyHourStart(newHour);
    }

    public boolean hasWon()  { return netWorth >= goalNetWorth; }
    public boolean hasLost() { return netWorth <= 0; }
}