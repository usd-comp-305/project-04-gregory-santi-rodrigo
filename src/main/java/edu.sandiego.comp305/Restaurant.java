package edu.sandiego.comp305;


// Restaurant interface which AbstractRestaurant will implement
public interface Restaurant {
    String getName();
    RestaurantType getType();

    boolean isOpen(int hour);
    int getOpenHour();
    int getCloseHour();

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

    RestaurantReport generateReport();

}
