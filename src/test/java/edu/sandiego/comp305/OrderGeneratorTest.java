package edu.sandiego.comp305;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderGeneratorTest {

    @Mock private Restaurant mockRestaurant;
    @Mock private MenuItem mockItem;

    @BeforeEach
    public void setUp() {
        when(mockRestaurant.getType()).thenReturn(RestaurantType.HOT_DOG);
        when(mockRestaurant.getMenu()).thenReturn(List.of(mockItem));
        for (int h = 0; h < 24; h++) {
            when(mockRestaurant.isOpen(h)).thenReturn(h >= 9 && h < 23);
            when(mockRestaurant.isHappyHour(h)).thenReturn(h == 18);
        }
    }

    @Test
    public void testGenerateDailyOrders_countIsAtLeast50() {
        OrderGenerator generator = new OrderGenerator();
        List<Order> orders = generator.generateDailyOrders(mockRestaurant);
        assertTrue(orders.size() >= 50,
                "Expected at least 50 orders but got: " + orders.size());
    }

    @Test
    public void testGenerateDailyOrders_countIsAtMost100() {
        OrderGenerator generator = new OrderGenerator();
        List<Order> orders = generator.generateDailyOrders(mockRestaurant);
        assertTrue(orders.size() <= 100,
                "Expected at most 100 orders but got: " + orders.size());
    }

    @Test
    public void testGenerateDailyOrders_allOrdersAreInOpenHours() {
        OrderGenerator generator = new OrderGenerator();
        List<Order> orders = generator.generateDailyOrders(mockRestaurant);
        for (Order order : orders) {
            assertTrue(mockRestaurant.isOpen(order.getHour()),
                    "Order placed at closed hour: " + order.getHour());
        }
    }

    @Test
    public void testGenerateDailyOrders_noOrdersInClosedHours() {
        OrderGenerator generator = new OrderGenerator();
        List<Order> orders = generator.generateDailyOrders(mockRestaurant);
        List<Integer> closedHours = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 23);
        for (Order order : orders) {
            assertFalse(closedHours.contains(order.getHour()),
                    "Order found at closed hour: " + order.getHour());
        }
    }

    @Test
    public void testGenerateDailyOrders_happyHourIsAboveAverageFrequency() {
        OrderGenerator generator = new OrderGenerator();
        int[] hourCounts = new int[24];
        int totalOrders = 0;

        for (int i = 0; i < 500; i++) {
            List<Order> orders = generator.generateDailyOrders(mockRestaurant);
            for (Order order : orders) {
                hourCounts[order.getHour()]++;
                totalOrders++;
            }
        }

        long openHourCount = 0;
        for (int h = 0; h < 24; h++) {
            if (mockRestaurant.isOpen(h)) openHourCount++;
        }

        double average = (double) totalOrders / openHourCount;
        assertTrue(hourCounts[18] > average * 1.5,
                String.format("Happy hour count %d should exceed 1.5x average %.1f",
                        hourCounts[18], average));
    }


}
