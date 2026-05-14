package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulatorTest {

    final static int RANDOM_VALUE = 42;

    final static int FROM_ZERO = 0;

    final static double THOUSANDTH_POWER = 0.001;

    private Simulator testSim;

    @BeforeEach
    public void setUp(){
        final List<Restaurant> testRestaurants = List.of(
                new BBQRestaurant());
        testSim = new Simulator(testRestaurants,
                new OrderGenerator(new Random(RANDOM_VALUE)));
    }

    @Test
    public void testRunDayReturnsNonNullReport(){
        assertNotNull(testSim.runDay(),
                "runDay() should never return null.");
    }

    @Test
    public void testRunDayResetsRevenueBeforeOrderGen(){
        final int DOUBLED = 2;
        final DailyReport dayOneReport = testSim.runDay();
        final double dayOneTotal = dayOneReport
                .getRestaurantReports().get(FROM_ZERO)
                .getTotalRevenue();

        final DailyReport dayTwoReport = testSim.runDay();
        final double dayTwoTotal = dayTwoReport
                .getRestaurantReports().get(FROM_ZERO)
                .getTotalRevenue();

        assertTrue(dayTwoTotal < dayOneTotal
                        * DOUBLED,
                "Day 2 revenue should not " +
                        "include day 1 revenue.");
    }

    @Test
    public void testRunDayDoesNotExceedRestaurantMaxOrders(){
        final DailyReport report = testSim.runDay();
        final RestaurantReport restaurantReport = report
                .getRestaurantReports().get(FROM_ZERO);
        final double maxPossibleRevenue = new BBQRestaurant()
                .getMaxOrdersPerDay()
                * BBQMenuItem.RIBS.getBasePrice();
        assertTrue(restaurantReport.getTotalRevenue()
                        <= maxPossibleRevenue,
                "Total revenue should not exceed what max " +
                        "orders could generate.");
    }

    @Test
    public void testRunDayReportContainsOnePerRestaurant(){
        final List<Restaurant> threeRestaurants = List.of(
                new BBQRestaurant(),
                new PizzaRestaurant(),
                new BurgerRestaurant()
        );
        final Simulator multiSim = new Simulator(
                threeRestaurants,
                new OrderGenerator(new Random(RANDOM_VALUE)));

        final DailyReport report = multiSim.runDay();

        assertEquals(threeRestaurants.size(), report
                        .getRestaurantReports().size(),
                "Report should contain exactly " +
                        "one entry per restaurant.");
    }

    @Test
    public void testRunDayOnlyGeneratesOrdersDuringOpenHours(){
        final BBQRestaurant testRestaurant = new BBQRestaurant();
        final Simulator singleSim = new Simulator(
                List.of(testRestaurant),
                new OrderGenerator(new Random(RANDOM_VALUE))
        );
        singleSim.runDay();

        for (Order testOrder: singleSim.getLastGeneratedOrders()){
            assertTrue(testRestaurant.isOpen(testOrder.getHour()),
                    "Order at hour " + testOrder.getHour()
                            + " was generated outside operating hours.");
        }
    }

    @Test
    public void testRunDayWithNoRestaurantsReturnsEmptyReport(){
        final double NO_NET_CHANGE = 0.0;
        final Simulator emptySim = new Simulator(List.of(), new OrderGenerator(
                new Random(RANDOM_VALUE)));
        final DailyReport testReport = emptySim.runDay();
        assertNotNull(testReport, "runDay() should not return " +
                "null even with no restaurants.");
        assertTrue(testReport.getRestaurantReports().isEmpty(),
                "Report should contain no restaurant reports " +
                        "when there are no restaurants.");
        assertEquals(NO_NET_CHANGE, testReport.getTotalNetChange(),
                THOUSANDTH_POWER,
                "Total net change should be zero when there" +
                        " are no restaurants.");
    }

    @Test
    public void testCurrentDayStartsAtOne(){
        final int DAY_ONE = 1;
        assertEquals(DAY_ONE, testSim.getCurrentDay());
    }

    @Test
    public void testCurrentDayIncrementsAfterEachRunDay(){
        final int DAY_TWO = 2;
        testSim.runDay();
        assertEquals(DAY_TWO, testSim.getCurrentDay());
    }

    @Test
    public void testPeakHourIsWithinOperatingHours(){
        final DailyReport testReport = testSim.runDay();
        final int peakHour = testReport.getRestaurantReports()
                .get(FROM_ZERO).getPeakHour();
        assertTrue(new BBQRestaurant().isOpen(peakHour),
                "Peak hour should fall within the " +
                        "restaurant's operating hours.");
    }

    @Test
    public void testPeakHourReflectsHighestOrderVolume() {
        final int NO_ORDERS = 0;
        final int MINIMUM_ORDERS = 1;
        final int MEDIAN_ORDERS = 3;
        final int MAXIMUM_ORDERS = 10;

        final int OPERATING_HOUR = 12;
        final int PEAK_HOUR = 14;
        final int ANOTHER_OPERATING_HOUR = 17;

        final List<Order> testOrders =
                new ArrayList<>();
        final MenuItem testItem = BBQMenuItem.RIBS;

        for (int orders = NO_ORDERS; orders < MAXIMUM_ORDERS;
             orders++) {
            testOrders.add(new Order(testItem, PEAK_HOUR,
                    RestaurantType.BBQ));
        }
        for (int orders = NO_ORDERS; orders < MEDIAN_ORDERS;
             orders++) {
            testOrders.add(new Order(testItem, OPERATING_HOUR,
                    RestaurantType.BBQ));
        }
        for (int orders = NO_ORDERS; orders < MINIMUM_ORDERS;
             orders++){
            testOrders.add(new Order(testItem, ANOTHER_OPERATING_HOUR,
                    RestaurantType.BBQ));
        }
        assertEquals(PEAK_HOUR, testSim.computePeakHour(
                testOrders),
                "Peak hour should be the hour with " +
                        "the most orders.");
    }

    @Test
    public void testDayReportTotalMatchesSumOfRestaurantTotals(){
        final List<Restaurant> twoRestaurants = List.of(
                new BBQRestaurant(), new PizzaRestaurant());
        final Simulator multiSim = new Simulator(
                twoRestaurants, new OrderGenerator(
                        new Random(RANDOM_VALUE)));

        final DailyReport testReport = multiSim.runDay();
        final double sumOfRestaurants = testReport
                .getRestaurantReports().stream()
                .mapToDouble(RestaurantReport::getTotalRevenue)
                .sum();
        assertEquals(sumOfRestaurants, testReport.getTotalNetChange(),
                THOUSANDTH_POWER,
                "DailyReport total should equal the sum of " +
                        "all restaurant totals.");
    }

    @Test
    public void testHappyHourRevenueIsLowerThanRegular(){
        final int GREATER_THAN_ZERO = 0;
        final DailyReport testReport = testSim.runDay();
        final RestaurantReport restaurantReport =
                testReport.getRestaurantReports().get(
                        FROM_ZERO);
        if (restaurantReport.getHappyHourRevenue()
                > GREATER_THAN_ZERO &&
            restaurantReport.getRegularRevenue()
                    > GREATER_THAN_ZERO){
            assertTrue(restaurantReport
                    .getHappyHourRevenue() <
                    restaurantReport.getRegularRevenue());
        }
    }

    @Test
    public void testRevenueIsPositiveAfterOrdersAreProcessed(){
        final DailyReport testReport = testSim.runDay();
        final double total = testReport.getRestaurantReports()
                .get(FROM_ZERO).getTotalRevenue();
        assertTrue(total > 0, "A restaurant " +
                "that received orders should have positive revenue.");
    }

    @Test
    public void testEachDayProducesIndependentRevenue(){
        final DailyReport dayOneReport = testSim.runDay();
        final DailyReport dayTwoReport = testSim.runDay();

        final double dayOneTotal = dayOneReport.getRestaurantReports()
                .get(FROM_ZERO).getTotalRevenue();
        final double dayTwoTotal = dayTwoReport.getRestaurantReports()
                .get(FROM_ZERO).getTotalRevenue();

        final double maxSingleDayRevenue = new BBQRestaurant()
                .getMaxOrdersPerDay()
                * BBQMenuItem.RIBS.getBasePrice();

        assertTrue(dayOneTotal <= maxSingleDayRevenue,
                "Day 1 revenue should not exceed one " +
                        "day's maximum possible revenue.");
        assertTrue(dayTwoTotal <= maxSingleDayRevenue,
                "Day 2 revenue should not exceed one " +
                        "day's maximum possible revenue.");
    }

    @Test
    public void testCurrentDayNumberAppearsInReport(){
        final int DAY_ONE = 1;
        final int DAY_TWO = 2;
        final DailyReport dayOneReport = testSim.runDay();
        assertEquals(DAY_ONE, dayOneReport.getDayNumber(),
                "First report should be labeled day 1.");

        final DailyReport dayTwoReport = testSim.runDay();
        assertEquals(DAY_TWO, dayTwoReport.getDayNumber(),
                "Second report should be labeled day 2.");
    }

    @Test
    public void testCreateAllRestaurantsReturnsCorrectCount() {
        final int EXPECTED_RESTAURANTS = 10;

        final List<Restaurant> restaurants =
                Simulator.createAllRestaurants();

        assertEquals(EXPECTED_RESTAURANTS, restaurants.size(),
                "createAllRestaurants() should create " +
                        "exactly 10 restaurants.");
    }

    @Test
    public void testCreateAllRestaurantsContainsBurgerRestaurant() {
        final List<Restaurant> restaurants =
                Simulator.createAllRestaurants();

        final boolean containsBurgerRestaurant =
                restaurants.stream()
                .anyMatch(restaurant ->
                        restaurant instanceof BurgerRestaurant);

        assertTrue(containsBurgerRestaurant,
                "Restaurant list should contain a " +
                        "BurgerRestaurant.");
    }


    @Test
    public void testRunDayGeneratesOrders() {
        testSim.runDay();

        assertFalse(testSim.getLastGeneratedOrders().isEmpty(),
                "runDay() should generate at least one order.");
    }
}
