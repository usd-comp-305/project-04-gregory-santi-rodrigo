package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private Order order;
    private final int TEST_HOUR = 12;
    private final RestaurantType TEST_TYPE = RestaurantType.BURGER;
    private final MenuItem TEST_ITEM = BurgerMenuItem.CHEESEBURGER;

    @BeforeEach
    void init() {
        order = new Order(TEST_ITEM, TEST_HOUR, TEST_TYPE);
    }

    @Test
    public void getItem_ReturnInitialItem() {
        assertEquals(TEST_ITEM, order.getItem(), "The returned item should match the input");
    }
}
