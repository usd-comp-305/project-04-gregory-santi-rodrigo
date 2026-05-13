package edu.sandiego.comp305;

import java.awt.*;
import java.util.List;

public abstract class AbstractRestaurant implements Restaurant{
    private static final double HAPPY_HOUR_DISCOUNT_RATE = 0.20;
    private static final double UPGRADE_ORDER_INCREASE = 0.50;
    private final PricingStrategy regularStrategy = new RegularPricingStrategy();
    private final PricingStrategy discountStrategy = new DiscountPricingStrategy();

    private final String name;
    private final RestaurantType type;
    private final int openHour;
    private final int closeHour;
    private final List<MenuItem> menu;
    private int happyHourStart;
    private int maxOrdersPerDay;
    private boolean upgraded;
    private double dailyRevenue;
    private double happyHourRevenue;

    public AbstractRestaurant(String name, RestaurantType type,
                              int openHour, int closeHour,
                              int defaultHappyHourStart, int baseMaxOrders,
                              List<MenuItem> menu) {
        this.name = name;
        this.type = type;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.happyHourStart = defaultHappyHourStart;
        this.maxOrdersPerDay = baseMaxOrders;
        this.menu = menu;
        this.upgraded = false;
        this.dailyRevenue = 0;
        this.happyHourRevenue = 0;

    }

    // general methods which all subclasses of AbstractRestaurant will inherit and need
    @Override public String getName()             { return name; }
    @Override public RestaurantType getType()     { return type; }
    @Override public int getOpenHour()            { return openHour; }
    @Override public int getCloseHour()           { return closeHour; }
    @Override public List<MenuItem> getMenu()     { return menu;}
    @Override public int getHappyHourStart()      { return happyHourStart; }
    @Override public int getMaxOrdersPerDay()     { return maxOrdersPerDay; }
    @Override public boolean isUpgraded()         { return upgraded; }
    @Override public double getDailyRevenue()     { return dailyRevenue; }
    @Override public double getHappyHourRevenue() { return happyHourRevenue; }
    @Override public double getTotalRevenue()     { return dailyRevenue + happyHourRevenue; }

    @Override
    public boolean isOpen(int hour) {
        if (openHour < closeHour) {
            return hour >= openHour && hour < closeHour;
        } else {
            return hour >= openHour || hour < closeHour;
        }
    }

    @Override
    public boolean isHappyHour(int hour) {
        return hour == happyHourStart && isOpen(hour);
    }

    @Override
    public void setHappyHourStart(int hour) {
        this.happyHourStart = hour;
    }

    @Override
    public void upgrade() {
        maxOrdersPerDay += (int)(maxOrdersPerDay * UPGRADE_ORDER_INCREASE);
        upgraded = true;
    }

    @Override
    public void resetDay() {
        dailyRevenue = 0;
        happyHourRevenue = 0;
    }


    private void recordRevenue(double amount, boolean duringHappyHour) {
        if (duringHappyHour) {
            happyHourRevenue += amount;
        } else {
            dailyRevenue += amount;
        }
    }

    @Override
    public RestaurantReport generateReport(int peakHour) {
        return new RestaurantReport(name, dailyRevenue, happyHourRevenue, peakHour, upgraded);
    }

    @Override
    public double processOrder(Order order) {
        boolean duringHappyHour = isHappyHour(order.getHour());
        PricingStrategy strategy = duringHappyHour ? discountStrategy : regularStrategy;
        double revenue = strategy.calculatePrice(order);
        recordRevenue(revenue, duringHappyHour);
        return revenue;
    }

}
