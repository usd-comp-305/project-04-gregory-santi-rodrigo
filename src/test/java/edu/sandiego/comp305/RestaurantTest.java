package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    private BurgerRestaurant restaurant;
    private final MenuItem TEST_ITEM = BurgerMenuItem.CHEESEBURGER;

    @BeforeEach
    void init() {
        restaurant = new BurgerRestaurant();
    }

    @Test
    public void isOpen_ReturnTrueDuringOperatingHours() {
        assertTrue(restaurant.isOpen(11), "Should be open at the exact opening hour (11)");
        assertTrue(restaurant.isOpen(15), "Should be open mid-day (15)");
        assertTrue(restaurant.isOpen(20), "Should be open one hour before closing (20)");
    }

    @Test
    public void isOpen_ReturnFalseOutsideOperatingHours() {
        assertFalse(restaurant.isOpen(10), "Should be closed before opening hour");
        assertFalse(restaurant.isOpen(21), "Should be closed at the exact closing hour (21)");
        assertFalse(restaurant.isOpen(22), "Should be closed after closing hour");
    }

    @Test
    public void isOpen_HotDogRestaurant_ReturnTrueForOvernightHours(){
        Restaurant hotDogRestaurant = new HotDogRestaurant();

        assertTrue(hotDogRestaurant.isOpen(22), "Should be open at 22");
        assertTrue(hotDogRestaurant.isOpen(0), "Should be open at midnight");
        assertTrue(hotDogRestaurant.isOpen(3), "Should be open at 3");
        assertTrue(hotDogRestaurant.isOpen(10), "Should be open before closing");

    }

    @Test
    public void isOpen_HotDogRestaurant_ReturnFalseForClosedHours(){
        Restaurant hotDogRestaurant = new HotDogRestaurant();

        assertFalse(hotDogRestaurant.isOpen(11), "Should be closed at the exact closing hour (11)");
        assertFalse(hotDogRestaurant.isOpen(12), "Should be closed after closing");
    }

    @Test
    public void isHappyHour_ReturnTrueDuringDefaultHappyHour() {
        assertTrue(restaurant.isHappyHour(15), "Should be true during default happy hour (15)");
    }

    @Test
    public void isHappyHour_ReturnFalseOutsideHappyHour() {
        assertFalse(restaurant.isHappyHour(14), "Should be false before happy hour");
        assertFalse(restaurant.isHappyHour(16), "Should be false after happy hour");
    }

    @Test
    public void setHappyHourStart_ReturnUpdateHappyHour() {
        int newHappyHour = 12;
        restaurant.setHappyHourStart(newHappyHour);

        assertEquals(newHappyHour, restaurant.getHappyHourStart(), "The happy hour start should update");
        assertTrue(restaurant.isHappyHour(12), "The new hour should now trigger happy hour");
    }

    @Test
    public void processOrder_ReturnFullPriceNormally() {
        Order normalOrder = new Order(TEST_ITEM, 12, RestaurantType.BURGER);
        double expectedPrice = TEST_ITEM.getBasePrice();
        double actualRevenue = restaurant.processOrder(normalOrder);

        assertEquals(expectedPrice, actualRevenue, "Normal order should return base price");
    }

    @Test
    public void processOrder_ReturnDiscountDuringHappyHour() {
        Order happyHourOrder = new Order(TEST_ITEM, 15, RestaurantType.BURGER);
        double expectedDiscountPrice = TEST_ITEM.getBasePrice() * 0.80;
        double actualRevenue = restaurant.processOrder(happyHourOrder);

        assertEquals(expectedDiscountPrice, actualRevenue, "Happy hour order should be 20% off");
    }

    @Test
    public void processOrder_ReturnAccumulateDailyRevenueForNormalOrders() {
        Order normalOrder = new Order(TEST_ITEM, 12, RestaurantType.BURGER);
        restaurant.processOrder(normalOrder);

        assertEquals(TEST_ITEM.getBasePrice(), restaurant.getDailyRevenue(), "Normal order should accumulate into daily revenue");
        assertEquals(0.0, restaurant.getHappyHourRevenue(), "Normal order should not accumulate into happy hour revenue");
    }

    @Test
    public void processOrder_ReturnAccumulateHappyHourRevenueForHappyHourOrders() {
        Order happyHourOrder = new Order(TEST_ITEM, 15, RestaurantType.BURGER);
        double expectedRevenue = TEST_ITEM.getBasePrice() * 0.80;
        restaurant.processOrder(happyHourOrder);

        assertEquals(0.0, restaurant.getDailyRevenue(), "Happy hour order should not accumulate into daily revenue");
        assertEquals(expectedRevenue, restaurant.getHappyHourRevenue(), "Happy hour order should accumulate into happy hour revenue");
    }

    @Test
    public void getTotalRevenue_ReturnEqualDailyRevenuePlusHappyHourRevenue() {
        Order normalOrder = new Order(TEST_ITEM, 12, RestaurantType.BURGER);
        Order happyHourOrder = new Order(TEST_ITEM, 15, RestaurantType.BURGER);
        restaurant.processOrder(normalOrder);
        restaurant.processOrder(happyHourOrder);

        double expectedTotal = restaurant.getDailyRevenue() + restaurant.getHappyHourRevenue();

        assertEquals(expectedTotal, restaurant.getTotalRevenue(), "Total revenue should equal daily revenue plus happy hour revenue");
    }

    @Test
    public void resetDay_ReturnClearRevenueAccumulators() {
        restaurant.processOrder(new Order(TEST_ITEM, 12, RestaurantType.BURGER));
        restaurant.processOrder(new Order(TEST_ITEM, 15, RestaurantType.BURGER));

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
        final int CLOSED_HOUR = 10;
        restaurant.setHappyHourStart(CLOSED_HOUR);

        assertFalse(restaurant.isHappyHour(CLOSED_HOUR), "Happy hour should not apply when the restaurant is closed");
    }
}
