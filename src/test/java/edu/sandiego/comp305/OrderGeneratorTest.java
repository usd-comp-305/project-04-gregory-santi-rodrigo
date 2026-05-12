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
}
