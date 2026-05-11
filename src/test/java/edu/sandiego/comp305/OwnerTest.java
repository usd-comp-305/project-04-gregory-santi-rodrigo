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
    public void testGetGoalNetWorthReturnsCorrectValue(){
        assertEquals(STARTING_NET_WORTH, testOwner.getNetWorth(), THOUSANDTH_DECIMAL,
                "getNetWorth() should return the starting net worth.");
    }

    @Test
    public void testApplyDailyReportIncreasesNetWorthOnProfit(){

    }

    @Test
    public void testApplyDailyReportDecreasesNetWorthOnLoss(){

    }

    @Test
    public void testApplyDailyReportAccumulatesAcrossMultipleDays(){

    }

    @Test
    public void testHasWonReturnsFalseWhenBelowGoal(){

    }

    @Test
    public void testHasWonReturnsTrueWhenGoalIsReached(){

    }

    @Test
    public void testHasLostReturnsFalseWhenAboveZero(){

    }

    @Test
    public void testHasLostReturnsTrueWhenNetWorthReachesZero(){

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
