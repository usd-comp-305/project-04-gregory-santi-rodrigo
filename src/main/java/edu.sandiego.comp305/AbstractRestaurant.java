package edu.sandiego.comp305;

// The Abstract class which eventually be extended to create concrete restaurant types
public abstract class AbstractRestaurant implements Restaurant{
    private static final double HAPPY_HOUR_DISCOUNT_RATE = 0.20;
    private static final int UPGRADE_ORDER_INCREASE = 25;

    private final PricingStrategy regularPricing = new RegularPricingStrategy();
    private final PricingStrategy discountPricing = new DiscountPricingStrategy();

    private final String name;
    private final RestaurantType type;
    private final int openHour;
    private final int closeHour;
    private int happyHourStart;
    private int maxOrdersPerDay;
    private boolean upgraded;
    private double dailyRevenue;
    private double happyHourRevenue;

    // general constructor
    public AbstractRestaurant(String name, RestaurantType type,
                              int openHour, int closeHour,
                              int defaultHappyHourStart, int baseMaxOrders) {
        this.name = name;
        this.type = type;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.happyHourStart = defaultHappyHourStart;
        this.maxOrdersPerDay = baseMaxOrders;
        this.upgraded = false;
        this.dailyRevenue = 0;
        this.happyHourRevenue = 0;

    }

    // general methods which all subclasses of AbstractRestaurant will inherit and need
    @Override public String getName()             { return name; }
    @Override public RestaurantType getType()     { return type; }
    @Override public int getOpenHour()            { return openHour; }
    @Override public int getCloseHour()           { return closeHour; }
    @Override public int getHappyHourStart()      { return happyHourStart; }
    @Override public int getMaxOrdersPerDay()     { return maxOrdersPerDay; }
    @Override public boolean isUpgraded()         { return upgraded; }
    @Override public double getDailyRevenue()     { return dailyRevenue; }
    @Override public double getHappyHourRevenue() { return happyHourRevenue; }
    @Override public double getTotalRevenue()     { return dailyRevenue + happyHourRevenue; }

    /**
     * Handles midnight-crossing hours (e.g. hot dog stand 20:00-11:00).
     */
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

    /** Each restaurant can only be upgraded once. */
    @Override
    public void upgrade() {
        if (!upgraded) {
            maxOrdersPerDay += UPGRADE_ORDER_INCREASE;
            upgraded = true;
        }
    }

    @Override
    public void resetDay() {
        dailyRevenue = 0;
        happyHourRevenue = 0;
    }

    /**
     * Shared revenue logic. Concrete classes call this from processOrder().
     * Happy hour applies a discount, reducing revenue but tracked separately.
     */
    protected double processOrderBase(Order order) {
        boolean duringHappyHour = isHappyHour(order.getHour());
        PricingStrategy strategy = duringHappyHour
                ? discountPricing
                : regularPricing;
        double revenue = strategy.calculatePrice(order);

        recordRevenue(revenue, duringHappyHour);
        return revenue;
    }

    private void recordRevenue(double amount, boolean duringHappyHour) {
        if (duringHappyHour) {
            happyHourRevenue += amount;
        } else {
            dailyRevenue += amount;
        }
    }

    @Override
    public RestaurantReport generateReport() {
        // peakHour is injected by Simulator after it computes it
        return new RestaurantReport(name, dailyRevenue, happyHourRevenue, 0);
    }

    @Override
    public abstract double processOrder(Order order);

}
