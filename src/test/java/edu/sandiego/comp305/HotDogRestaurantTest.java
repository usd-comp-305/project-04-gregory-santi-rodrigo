package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD Test class for HotDogRestaurant.isOpen()
 *
 * Restaurant hours: Opens at 20 (8pm), closes at 11 (11am).
 * This means it is open overnight: [20, 23] ∪ [0, 10] — i.e., any hour < CLOSE_HOUR or >= OPEN_HOUR.
 */

public class HotDogRestaurantTest {
    private HotDogRestaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new HotDogRestaurant();
    }

    // --- OPEN cases ---

    @Test
    public void testIsOpen_atOpeningHour_returnsTrue() {
        // Exactly at opening hour (8pm) — should be open
        assertTrue(restaurant.isOpen(20),
                "Restaurant should be open at hour 20 (its opening hour)");
    }


}
