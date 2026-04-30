package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private double netWorth;
    private final List<Restaurant> restaurants;
    private final double goalNetWorth;     // game ends in victory if reached

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
        // TODO: check if owner can afford the upgrade cost, deduct it, then call upgrade()
        restaurant.upgrade();
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