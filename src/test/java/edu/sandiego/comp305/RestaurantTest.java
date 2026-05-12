package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    private BurgerRestaurant restaurant;
    private final MenuItem TEST_ITEM = BurgerMenuItem.CHEESEBURGER;

    // Operating hours
    private final int OPEN_HOUR = 11;
    private final int MIDDAY = 15;
    private final int BEFORE_CLOSE = 20;
    private final int BEFORE_OPEN = 10;
    private final int CLOSED_HOUR = 21;
    private final int AFTER_CLOSED = 22;

    // HotDogRestaurant hours
    private final int HOTDOG_OPEN_LATE = 22;
    private final int HOTDOG_MIDNIGHT = 0;
    private final int HOTDOG_EARLY_MORNING = 3;
    private final int HOTDOG_BEFORE_CLOSE = 10;
    private final int HOTDOG_CLOSED = 11;
    private final int HOTDOG_AFTER_CLOSE = 12;

    // Happy hour
    private final int HAPPY_HOUR_START = 15;
    private final int NEW_HAPPY_HOUR = 12;
    private final int BEFORE_HAPPY_HOUR = HAPPY_HOUR_START - 1;
    private final int AFTER_HAPPY_HOUR = HAPPY_HOUR_START + 1;

    // Order hours
    private final int NORMAL_ORDER_HOUR = 12;
    private final int HAPPY_ORDER_HOUR = 15;

    // Pricing
    private final double DISCOUNT_RATE = 0.80;

    @BeforeEach
    void init() {
        restaurant = new BurgerRestaurant();
    }

    @Test
    public void isOpen_ReturnTrueDuringOperatingHours() {
        assertTrue(restaurant.isOpen(OPEN_HOUR), "Should be open at the exact opening hour (11)");
        assertTrue(restaurant.isOpen(MIDDAY), "Should be open mid-day (15)");
        assertTrue(restaurant.isOpen(BEFORE_CLOSE), "Should be open one hour before closing (20)");
    }

    @Test
    public void isOpen_ReturnFalseOutsideOperatingHours() {
        assertFalse(restaurant.isOpen(BEFORE_OPEN), "Should be closed before opening hour");
        assertFalse(restaurant.isOpen(CLOSED_HOUR), "Should be closed at the exact closing hour (21)");
        assertFalse(restaurant.isOpen(AFTER_CLOSED), "Should be closed after closing hour");
    }

    @Test
    public void isOpen_HotDogRestaurant_ReturnTrueForOvernightHours(){
        Restaurant hotDogRestaurant = new HotDogRestaurant();

        assertTrue(hotDogRestaurant.isOpen(HOTDOG_OPEN_LATE), "Should be open at 22");
        assertTrue(hotDogRestaurant.isOpen(HOTDOG_MIDNIGHT), "Should be open at midnight");
        assertTrue(hotDogRestaurant.isOpen(HOTDOG_EARLY_MORNING), "Should be open at 3");
        assertTrue(hotDogRestaurant.isOpen(HOTDOG_BEFORE_CLOSE), "Should be open before closing");

    }

    @Test
    public void isOpen_HotDogRestaurant_ReturnFalseForClosedHours(){
        Restaurant hotDogRestaurant = new HotDogRestaurant();

        assertFalse(hotDogRestaurant.isOpen(HOTDOG_CLOSED), "Should be closed at the exact closing hour (11)");
        assertFalse(hotDogRestaurant.isOpen(HOTDOG_AFTER_CLOSE), "Should be closed after closing");
    }

    @Test
    public void isHappyHour_ReturnTrueDuringDefaultHappyHour() {
        assertTrue(restaurant.isHappyHour(HAPPY_HOUR_START), "Should be true during default happy hour (15)");
    }

    @Test
    public void isHappyHour_ReturnFalseOutsideHappyHour() {
        assertFalse(restaurant.isHappyHour(BEFORE_HAPPY_HOUR), "Should be false before happy hour");
        assertFalse(restaurant.isHappyHour(AFTER_HAPPY_HOUR), "Should be false after happy hour");
    }

    @Test
    public void setHappyHourStart_ReturnUpdateHappyHour() {
        restaurant.setHappyHourStart(NEW_HAPPY_HOUR);

        assertEquals(NEW_HAPPY_HOUR, restaurant.getHappyHourStart(), "The happy hour start should update");
        assertTrue(restaurant.isHappyHour(NEW_HAPPY_HOUR), "The new hour should now trigger happy hour");
    }

    @Test
    public void processOrder_ReturnFullPriceNormally() {
        Order normalOrder = new Order(TEST_ITEM, NORMAL_ORDER_HOUR, RestaurantType.BURGER);
        double expectedPrice = TEST_ITEM.getBasePrice();
        double actualRevenue = restaurant.processOrder(normalOrder);

        assertEquals(expectedPrice, actualRevenue, "Normal order should return base price");
    }

    @Test
    public void processOrder_ReturnDiscountDuringHappyHour() {
        Order happyHourOrder = new Order(TEST_ITEM, HAPPY_ORDER_HOUR, RestaurantType.BURGER);
        double expectedDiscountPrice = TEST_ITEM.getBasePrice() * DISCOUNT_RATE;
        double actualRevenue = restaurant.processOrder(happyHourOrder);

        assertEquals(expectedDiscountPrice, actualRevenue, "Happy hour order should be 20% off");
    }

    @Test
    public void processOrder_ReturnAccumulateDailyRevenueForNormalOrders() {
        Order normalOrder = new Order(TEST_ITEM, NORMAL_ORDER_HOUR, RestaurantType.BURGER);
        restaurant.processOrder(normalOrder);

        assertEquals(TEST_ITEM.getBasePrice(), restaurant.getDailyRevenue(), "Normal order should accumulate into daily revenue");
        assertEquals(0.0, restaurant.getHappyHourRevenue(), "Normal order should not accumulate into happy hour revenue");
    }

    @Test
    public void processOrder_ReturnAccumulateHappyHourRevenueForHappyHourOrders() {
        Order happyHourOrder = new Order(TEST_ITEM, HAPPY_ORDER_HOUR, RestaurantType.BURGER);
        double expectedRevenue = TEST_ITEM.getBasePrice() * DISCOUNT_RATE;
        restaurant.processOrder(happyHourOrder);

        assertEquals(0.0, restaurant.getDailyRevenue(), "Happy hour order should not accumulate into daily revenue");
        assertEquals(expectedRevenue, restaurant.getHappyHourRevenue(), "Happy hour order should accumulate into happy hour revenue");
    }

    @Test
    public void getTotalRevenue_ReturnEqualDailyRevenuePlusHappyHourRevenue() {
        Order normalOrder = new Order(TEST_ITEM, NORMAL_ORDER_HOUR, RestaurantType.BURGER);
        Order happyHourOrder = new Order(TEST_ITEM, HAPPY_ORDER_HOUR, RestaurantType.BURGER);
        restaurant.processOrder(normalOrder);
        restaurant.processOrder(happyHourOrder);

        double expectedTotal = restaurant.getDailyRevenue() + restaurant.getHappyHourRevenue();

        assertEquals(expectedTotal, restaurant.getTotalRevenue(), "Total revenue should equal daily revenue plus happy hour revenue");
    }

    @Test
    public void resetDay_ReturnClearRevenueAccumulators() {
        restaurant.processOrder(new Order(TEST_ITEM, NORMAL_ORDER_HOUR, RestaurantType.BURGER));
        restaurant.processOrder(new Order(TEST_ITEM, HAPPY_ORDER_HOUR, RestaurantType.BURGER));

        restaurant.resetDay();

        assertEquals(0.0, restaurant.getDailyRevenue(), "Daily revenue should reset to 0");
        assertEquals(0.0, restaurant.getHappyHourRevenue(), "Happy hour revenue should reset to 0");
        assertEquals(0.0, restaurant.getTotalRevenue(), "Total revenue should reset to 0");
    }

    @Test
    public void upgrade_ReturnIncreaseMaxOrders() {
        int initialCapacity = restaurant.getMaxOrdersPerDay();
        restaurant.upgrade();

        assertEquals(initialCapacity + 25, restaurant.getMaxOrdersPerDay(), "Capacity should increase by 25");
        assertTrue(restaurant.isUpgraded(), "isUpgraded should return true");
    }

    @Test
    public void upgrade_ReturnOnlyIncreaseMaxOrdersOnce() {
        int initialCapacity = restaurant.getMaxOrdersPerDay();
        restaurant.upgrade();
        restaurant.upgrade();

        assertEquals(initialCapacity + 25, restaurant.getMaxOrdersPerDay(), "Calling upgrade twice should not increase more than once");
    }

    @Test
    public void isUpgraded_ReturnFalseBeforeUpgrade() {
        assertFalse(restaurant.isUpgraded(), "Restaurant should not be upgraded initially");
    }

    @Test
    public void isHappyHour_ReturnFalseWhenRestaurantIsClosed() {
        restaurant.setHappyHourStart(BEFORE_OPEN);

        assertFalse(restaurant.isHappyHour(BEFORE_OPEN), "Happy hour should not apply when the restaurant is closed");
    }
}
