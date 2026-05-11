package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {

    private static final double STARTING_NET_WORTH = 1000.00;

    private static final double GOAL_NET_WORTH = 5000.00;

    private static final int PEAK_HOUR = 14;

    private static final double THOUSANDTH_DECIMAL = 0.001;

    private Owner testOwner;
    private BBQRestaurant testRestaurant;

    @BeforeEach
    public void setUp(){
        testRestaurant = new BBQRestaurant();
        testOwner = new Owner(STARTING_NET_WORTH, GOAL_NET_WORTH,
                new ArrayList<>(List.of(testRestaurant)));
    }

    @Test
    public void testGetNetWorthReturnsStartingValue(){
        assertEquals(STARTING_NET_WORTH, testOwner.getNetWorth(), THOUSANDTH_DECIMAL,
                "getNetWorth() should return the starting net worth.");
    }

    @Test
    public void testGetGoalNetWorthReturnsCorrectValue(){
        assertEquals(GOAL_NET_WORTH, testOwner.getGoalNetWorth(), THOUSANDTH_DECIMAL,
                "getGoalNetWorth() should return the goal net worth.");
    }

    @Test
    public void testApplyDailyReportIncreasesNetWorthOnProfit(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 200.0;
        final double HAPPY_HOUR_REVENUE = 50.0;
        final double TOTAL_REVENUE = 250.0;

        DailyReport profitReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE, HAPPY_HOUR_REVENUE, PEAK_HOUR)
        ));
        testOwner.applyDailyReport(profitReport);
        assertEquals(STARTING_NET_WORTH + TOTAL_REVENUE, testOwner.getNetWorth(),
                THOUSANDTH_DECIMAL, "Net worth should increase after a profitable day.");
    }

    @Test
    public void testApplyDailyReportDecreasesNetWorthOnLoss(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = -200.0;
        final double HAPPY_HOUR_REVENUE = 0.0;
        final double TOTAL_REVENUE = 200.0;

        DailyReport lossReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE, HAPPY_HOUR_REVENUE, PEAK_HOUR)
        ));
        testOwner.applyDailyReport(lossReport);
        assertEquals(STARTING_NET_WORTH - TOTAL_REVENUE, testOwner.getNetWorth(),
                THOUSANDTH_DECIMAL, "Net worth should decrease after a losing day.");
    }

    @Test
    public void testApplyDailyReportAccumulatesAcrossMultipleDays(){
        final int DAY_NUMBER = 1;
        final int SECOND_DAY_NUMBER = 2;
        final double DAY_ONE_REGULAR_REVENUE = 200.0;
        final double DAY_ONE_HAPPY_HOUR_REVENUE = 50.0;
        final double DAY_TWO_REGULAR_REVENUE = 100.0;
        final double DAY_TWO_HAPPY_HOUR_REVENUE = 25.0;
        final double TOTAL_REVENUE = 375.0;

        DailyReport dayOne = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", DAY_ONE_REGULAR_REVENUE,
                        DAY_ONE_HAPPY_HOUR_REVENUE, PEAK_HOUR)
        ));
        DailyReport dayTwo = new DailyReport(SECOND_DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", DAY_TWO_REGULAR_REVENUE,
                        DAY_TWO_HAPPY_HOUR_REVENUE, PEAK_HOUR)
        ));
        testOwner.applyDailyReport(dayOne);
        testOwner.applyDailyReport(dayTwo);
        assertEquals(STARTING_NET_WORTH + TOTAL_REVENUE, testOwner.getNetWorth(),
                THOUSANDTH_DECIMAL, "Net worth should accumulate correctly across multiple days.");
    }

    @Test
    public void testHasWonReturnsFalseWhenBelowGoal(){
        assertFalse(testOwner.hasWon(),
                "hasWon() should return false when net worth is below goal.");
    }

    @Test
    public void testHasWonReturnsTrueWhenGoalIsReached(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 4000.0;
        final double HAPPY_HOUR_REVENUE = 0.0;

        DailyReport bigReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        HAPPY_HOUR_REVENUE, PEAK_HOUR)
        ));
        testOwner.applyDailyReport(bigReport);
        assertTrue(testOwner.hasWon(),
                "hasWon() should return true when net worth reaches or exceeds goal.");
    }

    @Test
    public void testHasLostReturnsFalseWhenAboveZero(){
        assertFalse(testOwner.hasLost(),
                "hasLost() should return false when net worth is above zero.");
    }

    @Test
    public void testHasLostReturnsTrueWhenNetWorthReachesZero(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = -1000.0;
        final double HAPPY_HOUR_REVENUE = 0.0;

        DailyReport wipeoutReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        HAPPY_HOUR_REVENUE, PEAK_HOUR)
        ));
        testOwner.applyDailyReport(wipeoutReport);
        assertTrue(testOwner.hasLost(),
                "hasLost() should return true when net worth reaches zero.");
    }

    @Test
    public void testHasLostReturnsTrueWhenNetWorthGoesNegative(){

    }

    @Test
    public void testShutdownRestaurantRemovesItFromList(){

    }

    @Test
    public void testShutdownRestaurantLeavesOthersIntact(){

    }

    @Test
    public void testShutdownLastRestaurantLeavesEmptyList(){

    }

    @Test
    public void testUpgradeIncreasesRestaurantMaxOrders(){

    }

    @Test
    public void testUpgradeCanOnlyHappenOnce(){

    }

    @Test
    public void testChangeHappyHourUpdatesCorrectRestaurant(){

    }

    @Test
    public void testChangeHappyHourDoesNotAffectOtherRestaurants(){

    }
}
