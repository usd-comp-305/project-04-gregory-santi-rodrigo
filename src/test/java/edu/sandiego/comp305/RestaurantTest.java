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
}
