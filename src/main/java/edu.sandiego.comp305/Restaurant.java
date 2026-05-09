package edu.sandiego.comp305;

import java.util.List;

public interface Restaurant {
    String getName();
    RestaurantType getType();

    boolean isOpen(int hour);
    int getOpenHour();
    int getCloseHour();

    List<MenuItem> getMenu();

    double processOrder(Order order);
    boolean isHappyHour(int hour);
    int getHappyHourStart();
    void setHappyHourStart(int hour);

    int getMaxOrdersPerDay();
    void upgrade();
    boolean isUpgraded();

    void resetDay();
    double getDailyRevenue();
    double getHappyHourRevenue();
    double getTotalRevenue();

    RestaurantReport generateReport(int peakHour);

}
