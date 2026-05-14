package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {

    private static final double STARTING_NET_WORTH = 25000.00;

    private static final double GOAL_NET_WORTH = 50000.00;

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
        assertEquals(STARTING_NET_WORTH, testOwner.getNetWorth(),
                THOUSANDTH_DECIMAL,
                "getNetWorth() should return the starting net worth.");
    }

    @Test
    public void testGetGoalNetWorthReturnsCorrectValue(){
        assertEquals(GOAL_NET_WORTH, testOwner.getGoalNetWorth(),
                THOUSANDTH_DECIMAL,
                "getGoalNetWorth() should return the goal net worth.");
    }

    @Test
    public void testHasWonReturnsFalseWhenBelowGoal(){
        assertFalse(testOwner.hasWon(),
                "hasWon() should return false when net worth is below goal.");
    }

    @Test
    public void testHasWonReturnsTrueWhenGoalIsReached(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 50000.0;
        final double HAPPY_HOUR_REVENUE = 0.0;

        final DailyReport bigReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        HAPPY_HOUR_REVENUE, PEAK_HOUR, false,0,0,0)
        ));
        testOwner.applyDailyReport(bigReport);
        assertTrue(testOwner.hasWon(),
                "hasWon() should return true when net worth " +
                        "reaches or exceeds goal.");
    }

    @Test
    public void testHasLostReturnsFalseWhenAboveZero(){
        assertFalse(testOwner.hasLost(),
                "hasLost() should return false when net worth is above zero.");
    }

    @Test
    public void testHasLostReturnsTrueWhenNetWorthReachesZero(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = -50000.0;
        final double HAPPY_HOUR_REVENUE = 0.0;

        final DailyReport wipeoutReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        HAPPY_HOUR_REVENUE, PEAK_HOUR, false,0,0,0)
        ));
        testOwner.applyDailyReport(wipeoutReport);
        assertTrue(testOwner.hasLost(),
                "hasLost() should return true when net worth reaches zero.");
    }

    @Test
    public void testHasLostReturnsTrueWhenNetWorthGoesNegative(){
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = -60000.0;
        final double HAPPY_HOUR_REVENUE = 0.0;

        final DailyReport wipeoutReport = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        HAPPY_HOUR_REVENUE, PEAK_HOUR, false,0,0,0)
        ));
        testOwner.applyDailyReport(wipeoutReport);
        assertTrue(testOwner.hasLost(),
                "hasLost() should return true when net worth drops below " +
                        "zero.");
    }

    @Test
    public void testShutdownRestaurantRemovesItFromList(){
        testOwner.shutdownRestaurant(testRestaurant);
        assertFalse(testOwner.getRestaurants().contains(testRestaurant),
                "Shutdown restaurant should be removed from the owner's list.");
    }

    @Test
    public void testShutdownRestaurantLeavesOthersIntact(){
        final BBQRestaurant secondRestaurant = new BBQRestaurant();
        final Owner ownerWithTwo = new Owner(STARTING_NET_WORTH, GOAL_NET_WORTH,
                new ArrayList<>(List.of(testRestaurant, secondRestaurant)));
        ownerWithTwo.shutdownRestaurant(testRestaurant);
        assertTrue(ownerWithTwo.getRestaurants().contains(secondRestaurant),
                "Shutting down one restaurant should not affect others.");
    }

    @Test
    public void testShutdownLastRestaurantLeavesEmptyList(){
        testOwner.shutdownRestaurant(testRestaurant);
        assertTrue(testOwner.getRestaurants().isEmpty(),
                "Shutting down the last restaurant " +
                        "should leave an empty list.");
    }

    @Test
    public void testUpgradeIncreasesRestaurantMaxOrders(){
        final int beforeUpgrade = testRestaurant.getMaxOrdersPerDay();
        testOwner.upgrade(testRestaurant);
        assertTrue(testRestaurant.getMaxOrdersPerDay() > beforeUpgrade,
                "Upgrading should increase the restaurant's max orders " +
                        "per day.");
    }

    @Test
    public void testUpgradeCanOnlyHappenOnce(){
        testOwner.upgrade(testRestaurant);
        final int afterFirstUpgrade = testRestaurant.getMaxOrdersPerDay();
        testOwner.upgrade(testRestaurant);
        assertEquals(afterFirstUpgrade, testRestaurant.getMaxOrdersPerDay(),
                "A restaurant should not be upgradeable more than once.");
    }

    @Test
    public void testChangeHappyHourUpdatesCorrectRestaurant(){
        final int NEW_HAPPY_HOUR = 16;
        testOwner.changeHappyHour(testRestaurant, NEW_HAPPY_HOUR);
        assertEquals(NEW_HAPPY_HOUR, testRestaurant.getHappyHourStart(),
                "changeHappyHour() should update the " +
                        "restaurant's happy hour start.");
    }

    @Test
    public void testChangeHappyHourDoesNotAffectOtherRestaurants(){
        final int NEW_HAPPY_HOUR = 16;
        final BBQRestaurant secondRestaurant = new BBQRestaurant();
        final int originalHappyHour = secondRestaurant.getHappyHourStart();
        final Owner ownerWithTwo = new Owner(STARTING_NET_WORTH, GOAL_NET_WORTH,
                new ArrayList<>(List.of(testRestaurant, secondRestaurant)));

        ownerWithTwo.changeHappyHour(testRestaurant, NEW_HAPPY_HOUR);

        assertEquals(originalHappyHour, secondRestaurant.getHappyHourStart(),
                "Changing happy hour on one restaurant should not " +
                        "affect others.");
    }

    @Test
    public void testApplyDailyReportCalculatesRegularProfitCorrectly() {
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 200.0;
        final double EXPECTED_REGULAR_PROFIT = REGULAR_REVENUE * 0.70;

        final DailyReport report = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        0, PEAK_HOUR, false, 0, 0, 0)
        ));
        final DailyReport profitReport = testOwner.applyDailyReport(report);
        assertEquals(EXPECTED_REGULAR_PROFIT,
                profitReport.getRestaurantReports().get(0).getRegularProfit(),
                THOUSANDTH_DECIMAL,
                "Regular profit should be 70% of regular revenue.");
    }

    @Test
    public void testApplyDailyReportCalculatesHappyHourProfitCorrectly() {
        final int DAY_NUMBER = 1;
        final double HAPPY_HOUR_REVENUE = 80.0;
        final double ORIGINAL_PRICE = HAPPY_HOUR_REVENUE
                / 0.80;
        final double EXPECTED_HH_PROFIT = HAPPY_HOUR_REVENUE -
                (ORIGINAL_PRICE * 0.30);

        final DailyReport report = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", 0,
                        HAPPY_HOUR_REVENUE, PEAK_HOUR, false, 0, 0, 0)
        ));
        final DailyReport profitReport = testOwner.applyDailyReport(report);
        assertEquals(EXPECTED_HH_PROFIT,
                profitReport.getRestaurantReports().get(0).getHappyHourProfit(),
                THOUSANDTH_DECIMAL,
                "Happy hour profit should be revenue minus 30% of " +
                        "original pre-discount price.");
    }

    @Test
    public void
        testApplyDailyReportDeductsCorrectExpenseForNonUpgradedRestaurant() {
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 1000.0;
        final double EXPECTED_EXPENSE = 500.0;
        final double EXPECTED_NET_WORTH = STARTING_NET_WORTH
                + (REGULAR_REVENUE * 0.70)
                - EXPECTED_EXPENSE;

        final DailyReport report = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE,
                        0, PEAK_HOUR, false, 0, 0, 0)
        ));
        testOwner.applyDailyReport(report);
        assertEquals(EXPECTED_NET_WORTH, testOwner.getNetWorth(),
                THOUSANDTH_DECIMAL,
                "Non-upgraded restaurant should have $500 daily expense " +
                        "deducted.");
    }

    @Test
    public void
        testApplyDailyReportDeductsCorrectExpenseForUpgradedRestaurant() {
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 1000.0;
        final double UPGRADE_COST = 1000.0;
        final double UPGRADED_EXPENSE = 1000.0;
        final double EXPECTED_NET_WORTH = STARTING_NET_WORTH
                - UPGRADE_COST
                + (REGULAR_REVENUE * 0.70)
                - UPGRADED_EXPENSE;

        testOwner.upgrade(testRestaurant);

        final DailyReport report = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack", REGULAR_REVENUE, 0, PEAK_HOUR,
                        testRestaurant.isUpgraded(), 0, 0, 0)
        ));
        testOwner.applyDailyReport(report);
        assertEquals(EXPECTED_NET_WORTH, testOwner.getNetWorth(),
                THOUSANDTH_DECIMAL,
                "Upgraded restaurant should have $1000 daily expense " +
                        "deducted.");
    }

    @Test
    public void testApplyDailyReportReturnsProfitPopulatedReport() {
        final int DAY_NUMBER = 1;
        final double REGULAR_REVENUE = 200.0;
        final double HAPPY_HOUR_REVENUE = 80.0;

        final DailyReport report = new DailyReport(DAY_NUMBER, List.of(
                new RestaurantReport("BBQ Shack",
                        REGULAR_REVENUE, HAPPY_HOUR_REVENUE, PEAK_HOUR,
                        false, 0, 0, 0)
        ));
        final DailyReport profitReport = testOwner.applyDailyReport(report);
        assertNotEquals(0, profitReport.getRestaurantReports()
                        .get(0).getRegularProfit(),
                "Returned report should have non-zero regular profit.");
        assertNotEquals(0, profitReport.getRestaurantReports()
                        .get(0).getHappyHourProfit(),
                "Returned report should have non-zero happy hour profit.");
    }


    @Test
    public void testUpgradeDeductsFromNetWorth() {
        final double UPGRADE_COST = 1000.0;
        testOwner.upgrade(testRestaurant);
        assertEquals(STARTING_NET_WORTH - UPGRADE_COST, testOwner
                        .getNetWorth(), THOUSANDTH_DECIMAL,
                "Upgrading should deduct $1000 from net worth.");
    }

    @Test
    public void testUpgradeDoesNothingWhenCannotAfford() {
        final Owner poorOwner = new Owner(500.0, GOAL_NET_WORTH,
                new ArrayList<>(List.of(testRestaurant)));
        poorOwner.upgrade(testRestaurant);
        assertFalse(testRestaurant.isUpgraded(),
                "Upgrade should not apply when owner cannot afford it.");
        assertEquals(500.0, poorOwner.getNetWorth(), THOUSANDTH_DECIMAL,
                "Net worth should not change when upgrade is rejected.");
    }

    @Test
    public void testChangeHappyHourRejectsClosedHour() {
        final int CLOSED_HOUR = 3;
        final int originalHappyHour = testRestaurant.getHappyHourStart();
        testOwner.changeHappyHour(testRestaurant, CLOSED_HOUR);
        assertEquals(originalHappyHour, testRestaurant.getHappyHourStart(),
                "changeHappyHour() should not update if the " +
                        "restaurant is closed at that hour.");
    }

    @Test
    public void testChangeHappyHourAcceptsOpenHour() {
        final int OPEN_HOUR = 14;
        testOwner.changeHappyHour(testRestaurant, OPEN_HOUR);
        assertEquals(OPEN_HOUR, testRestaurant.getHappyHourStart(),
                "changeHappyHour() should update when the " +
                        "restaurant is open at that hour.");
    }
}
