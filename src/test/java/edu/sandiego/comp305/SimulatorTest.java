package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Random;

public class SimulatorTest {

    private Simulator testSim;

    final static int RANDOM_VALUE = 42;

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
    public void testRunDayIncreasesCurrentDay(){

    }

    @Test
    public void testRunDayResetsRevenueBeforeOrderGen(){

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

    }

    @Test
    public void testEachDayProducesIndependentRevenue(){

    }

    @Test
    public void testCurrentDayNumberAppearsInReport(){

    }
}
