package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    private static final int TEST_HOUR = 12;

    private static final RestaurantType TEST_TYPE = RestaurantType.BURGER;

    private static final MenuItem TEST_ITEM = BurgerMenuItem.CHEESEBURGER;

    private Order order;

    @BeforeEach
    void init() {
        order = new Order(TEST_ITEM, TEST_HOUR, TEST_TYPE);
    }

    @Test
    public void getItem_ReturnInitialItem() {
        assertEquals(TEST_ITEM, order.getItem(),
                "The returned item should match the input");
    }

    @Test
    public void getHour_ReturnInitialHour() {
        assertEquals(TEST_HOUR, order.getHour(),
                "The returned hour should match the input");
    }

    @Test
    public void getRestaurantType_ReturnInitialType() {
        assertEquals(TEST_TYPE, order.getRestaurantType(),
                "The returned type should match the input");
    }
}
