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

    private static final int HAPPY_HOUR = 18;

    private static final int OPEN_HOUR = 9;

    private static final int CLOSE_HOUR = 23;

    private static final int NUM_STATISTICAL_RUNS = 10;

    private static final double SKEW_THRESHOLD = 1.5;

    private static final int NON_RUSH_HAPPY_HOUR = 10;

    private static final int LUNCH_RUSH_HOUR = 12;

    private static final int DINNER_RUSH_HOUR = 18;

    private static final int FIRST_HOUR_OF_DAY = 0;

    private static final int LAST_HOUR_OF_DAY = 24;

    private static final int MIN_ORDER_SIZE = 1000;

    private static final int MAX_ORDER_SIZE = 2000;

    private static final int FIRST_ORDER = 100;

    @Mock private Restaurant mockRestaurant;

    @Mock private MenuItem mockItem;

    @BeforeEach
    public void setUp() {
        when(mockRestaurant.getType()).thenReturn(RestaurantType.HOT_DOG);
        when(mockRestaurant.getMenu()).thenReturn(List.of(mockItem));
        for (int h = FIRST_HOUR_OF_DAY; h < LAST_HOUR_OF_DAY; h++) {
            when(mockRestaurant.isOpen(h)).thenReturn(h >= OPEN_HOUR &&
                     h < CLOSE_HOUR);
        }
    }

    @Test
    public void testGenerateDailyOrders_countIsAtLeast1000() {
        final OrderGenerator generator = new OrderGenerator();
        final List<Order> orders = generator.generateDailyOrders(
                mockRestaurant);
        assertTrue(orders.size() >= MIN_ORDER_SIZE,
                "Expected at least 50 orders but got: " + orders.size());
    }

    @Test
    public void testGenerateDailyOrders_countIsAtMost2000() {
        final OrderGenerator generator = new OrderGenerator();
        final List<Order> orders = generator.generateDailyOrders(
                mockRestaurant);
        assertTrue(orders.size() <= MAX_ORDER_SIZE,
                "Expected at most 100 orders but got: " + orders.size());
    }


    @Test
    public void testGenerateDailyOrders_noOrdersInClosedHours() {

        final OrderGenerator generator = new OrderGenerator();
        final List<Order> orders = generator.generateDailyOrders(
                mockRestaurant);
        final List<Integer> closedHours = List.of(
                0, 1, 2, 3, 4, 5, 6, 7, 8, 23);
        for (Order order : orders) {
            assertFalse(closedHours.contains(order.getHour()),
                    "Order found at closed hour: " + order.getHour());
        }
    }

    @Test
    public void testGenerateDailyOrders_happyHourIsAboveAverageFrequency() {

        stubHappyHour(HAPPY_HOUR);
        final OrderGenerator generator = new OrderGenerator();
        final int[] hourCounts = new int[LAST_HOUR_OF_DAY];
        int totalOrders = 0;

        for (int i = 0; i < NUM_STATISTICAL_RUNS; i++) {
            final List<Order> orders = generator.generateDailyOrders(
                    mockRestaurant);
            for (Order order : orders) {
                hourCounts[order.getHour()]++;
                totalOrders++;
            }
        }

        long openHourCount = 0;
        for (int h = 0; h < LAST_HOUR_OF_DAY; h++) {
            if (mockRestaurant.isOpen(h)) {
                openHourCount++;
            }
        }

        final double average = (double) totalOrders / openHourCount;
        assertTrue(hourCounts[HAPPY_HOUR] > average
                        * SKEW_THRESHOLD,
                String.format("Happy hour count %d should exceed " +
                                "1.5x average %.1f",
                        hourCounts[HAPPY_HOUR], average));
    }

    @Test
    public void testGenerateDailyOrders_rushHoursAreAboveAverageFrequency() {

        stubHappyHour(NON_RUSH_HAPPY_HOUR);

        final OrderGenerator generator = new OrderGenerator();
        final int[] hourCounts = new int[LAST_HOUR_OF_DAY];
        int totalOrders = 0;

        for (int i = 0; i < NUM_STATISTICAL_RUNS; i++) {
            final List<Order> orders = generator.generateDailyOrders(
                    mockRestaurant);
            for (Order order : orders) {
                hourCounts[order.getHour()]++;
                totalOrders++;
            }
        }

        long openHourCount = 0;
        for (int h = 0; h < LAST_HOUR_OF_DAY; h++) {
            if (mockRestaurant.isOpen(h)) {
                openHourCount++;
            }
        }

        final double average = (double) totalOrders / openHourCount;

        assertTrue(hourCounts[LUNCH_RUSH_HOUR] > average * SKEW_THRESHOLD,
                String.format("Lunch rush count %d should exceed 1.5x " +
                                "average %.1f",
                        hourCounts[LUNCH_RUSH_HOUR], average));
        assertTrue(hourCounts[DINNER_RUSH_HOUR] > average * SKEW_THRESHOLD,
                String.format("Dinner rush count %d should exceed 1.5x " +
                                "average %.1f", hourCounts[DINNER_RUSH_HOUR],
                        average));
    }

    @Test
    public void testGenerateDailyOrders_allItemsAreFromRestaurantMenu() {

        final OrderGenerator generator = new OrderGenerator();

        final List<Order> orders = generator.generateDailyOrders(
                mockRestaurant);

        final List<MenuItem> menu = mockRestaurant.getMenu();

        for (Order order : orders) {
            assertTrue(menu.contains(order.getItem()),
                    "Order contains item not on the restaurant's menu");
        }
    }

    @Test
    public void testGenerateDailyOrders_twiceProducesDifferentResults() {

        final OrderGenerator generator = new OrderGenerator();
        boolean foundDifference = false;

        for (int attempt = 0; attempt < NUM_STATISTICAL_RUNS; attempt++) {
            final List<Order> first  = generator.generateDailyOrders(
                    mockRestaurant);
            final List<Order> second = generator.generateDailyOrders(
                    mockRestaurant);

            final boolean identical = first.size() == second.size()
                    && first.get(FIRST_ORDER).getHour() ==
                    second.get(FIRST_ORDER).getHour()
                    && first.get(FIRST_ORDER).getItem()  ==
                    second.get(FIRST_ORDER).getItem();

            if (!identical) {
                foundDifference = true;
                break;
            }
        }

        assertTrue(foundDifference,
                "generateDailyOrders() appears deterministic — " +
                        "results were always identical");
    }

    private void stubHappyHour(final int happyHour) {
        for (int h = OPEN_HOUR; h < CLOSE_HOUR; h++) {
            when(mockRestaurant.isHappyHour(h)).thenReturn(h == happyHour);
        }
    }


}
