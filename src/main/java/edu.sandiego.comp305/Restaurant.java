package edu.sandiego.comp305;

import java.util.List;

public interface Restaurant {

    public abstract String getName();

    public abstract RestaurantType getType();

    public abstract  boolean isOpen(int hour);

    public abstract int getOpenHour();

    public abstract int getCloseHour();

    public abstract List<MenuItem> getMenu();

    public abstract double processOrder(Order order);

    public abstract boolean isHappyHour(int hour);

    public abstract int getHappyHourStart();

    public abstract void setHappyHourStart(int hour);

    public abstract int getMaxOrdersPerDay();

    public abstract void upgrade();

    public abstract boolean isUpgraded();

    public abstract void resetDay();

    public abstract double getDailyRevenue();

    public abstract double getHappyHourRevenue();

    public abstract double getTotalRevenue();

    public abstract RestaurantReport generateReport(int peakHour);

}
