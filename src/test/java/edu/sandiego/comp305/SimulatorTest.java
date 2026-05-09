package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Random;

public class SimulatorTest {

    private Simulator testSim;

    final static int RANDOM_VALUE = 42;
    final static int FROM_ZERO = 0;

    @BeforeEach
    public void setUp(){
        List<Restaurant> testRestaurants = List.of(new BBQRestaurant());
        testSim = new Simulator(testRestaurants, new OrderGenerator(new Random(RANDOM_VALUE)));
    }

    @Test
    public void testRunDayReturnsNonNullReport(){
        assertNotNull(testSim.runDay(), "runDay() should never return null.");
    }

    @Test
    public void testRunDayResetsRevenueBeforeOrderGen(){
        final int DOUBLED = 2;
        DailyReport dayOneReport = testSim.runDay();
        double dayOneTotal = dayOneReport.getRestaurantReports().get(FROM_ZERO).getTotalRevenue();

        DailyReport dayTwoReport = testSim.runDay();
        double dayTwoTotal = dayTwoReport.getRestaurantReports().get(FROM_ZERO).getTotalRevenue();

        assertTrue(dayTwoTotal < dayOneTotal * DOUBLED,
                "Day 2 revenue should not include day 1 revenue.");
    }

    @Test
    public void testRunDayDoesNotExceedRestaurantMaxOrders(){

    }

    @Test
    public void testRunDayReportContainsOnePerRestaurant(){

    }

    @Test
    public void testRunDayOnlyGeneratesOrdersDuringOpenHours(){

    }

    @Test
    public void testRunDayWithNoRestaurantsReturnsEmptyReport(){

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

    }

    @Test
    public void testPeakHourReflectsHighestOrderVolume(){

    }

    @Test
    public void testDayReportTotalMatchesSumOfRestaurantTotals(){

    }

    @Test
    public void testHappyHourRevenueIsLowerThanRegularForSameItem(){

    }

    @Test
    public void testRevenueIsPositiveAfterOrdersAreProcessed(){
        DailyReport testReport = testSim.runDay();
        double total = testReport.getRestaurantReports().get(FROM_ZERO).getTotalRevenue();
        assertTrue(total > 0, "A restaurant that received orders should have positive revenue.");
    }

    @Test
    public void testEachDayProducesIndependentRevenue(){

    }

    @Test
    public void testCurrentDayNumberAppearsInReport(){
        testSim.runDay();

    }
}
