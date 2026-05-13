package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantReportTest {

    private static final String TEST_NAME = "BBQ Shack";

    private static final double REGULAR_REVENUE = 100.00;

    private static final double HAPPY_REVENUE = 40.00;

    private static final double ZERO_REVENUE = 0.0;

    private static final double NEGATIVE_REVENUE = -50.0;

    private static final int PEAK_HOUR = 14;

    private static final double THOUSANDTH_DECIMAL = 0.001;

    private RestaurantReport buildReport(){
        return new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR, false);
    }

    @Test
    public void testGetRestaurantNameReturnsCorrectName(){
        assertEquals(TEST_NAME, buildReport().getRestaurantName(),
                "getRestaurantName() should return the name passed to the constructor.");
    }

    @Test
    public void testGetRegularRevenueReturnsCorrectValue(){
        assertEquals(REGULAR_REVENUE, buildReport().getRegularRevenue(), THOUSANDTH_DECIMAL,
                "getRegularRevenue() should return the regular revenue passed in.");
    }

    @Test
    public void testGetHappyHourRevenueReturnsCorrectValue(){
        assertEquals(HAPPY_REVENUE, buildReport().getHappyHourRevenue(), THOUSANDTH_DECIMAL,
                "getHappyHourRevenue() should return the happy hour revenue passed in.");
    }

    @Test
    public void testGetTotalRevenueIsRegularPlusHappyHour(){
        assertEquals(REGULAR_REVENUE + HAPPY_REVENUE, buildReport().getTotalRevenue(), THOUSANDTH_DECIMAL,
                "getTotalRevenue() should equal regular + happy hour revenue.");
    }

    @Test
    public void testGetTotalRevenueWithZeroHappyHour(){
        RestaurantReport report = new RestaurantReport(TEST_NAME, REGULAR_REVENUE, ZERO_REVENUE, PEAK_HOUR, false);
        assertEquals(REGULAR_REVENUE, report.getTotalRevenue(), THOUSANDTH_DECIMAL,
                "getTotalRevenue() should equal regular revenue when happy hour is zero.");
    }

    @Test
    public void testGetTotalRevenueWithZeroRegular(){
        RestaurantReport report = new RestaurantReport(TEST_NAME, ZERO_REVENUE, HAPPY_REVENUE, PEAK_HOUR, false);
        assertEquals(HAPPY_REVENUE, report.getTotalRevenue(), THOUSANDTH_DECIMAL,
                "getTotalRevenue() should equal happy hour revenue when regular is zero.");
    }

    @Test
    public void testGetTotalRevenueWithBothZero(){
        RestaurantReport report = new RestaurantReport(TEST_NAME, ZERO_REVENUE, ZERO_REVENUE, PEAK_HOUR, false);
        assertEquals(ZERO_REVENUE, report.getTotalRevenue(), THOUSANDTH_DECIMAL,
                "getTotalRevenue() should be zero when both revenues are zero.");
    }

    @Test
    public void testGetTotalRevenueIsNegativeWhenRevenueIsNegative(){
        RestaurantReport report = new RestaurantReport(TEST_NAME, NEGATIVE_REVENUE, ZERO_REVENUE, PEAK_HOUR, false);
        assertTrue(report.getTotalRevenue() < 0,
                "getTotalRevenue() should be negative when the restaurant lost money.");
    }

    @Test
    public void testGetPeakHourReturnsCorrectHour(){
        assertEquals(PEAK_HOUR, buildReport().getPeakHour(),
                "getPeakHour() should return the peak hour passed to the constructor.");
    }

    @Test
    public void testToStringIsNotEmpty(){
        assertFalse(buildReport().toString().isEmpty(),
                "toString() should return a non-empty string once implemented.");
    }

    @Test
    public void testIsUpgradedReturnsFalseWhenNotUpgraded() {
        RestaurantReport report = new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR, false);
        assertFalse(report.isUpgraded(),
                "isUpgraded() should return false when constructed with upgraded=false.");
    }

    @Test
    public void testIsUpgradedReturnsTrueWhenUpgraded() {
        RestaurantReport report = new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR, true);
        assertTrue(report.isUpgraded(),
                "isUpgraded() should return true when constructed with upgraded=true.");
    }

    @Test
    public void testToStringContainsUpgradedLabelWhenUpgraded() {
        RestaurantReport report = new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR, true);
        assertTrue(report.toString().contains("[UPGRADED]"),
                "toString() should contain '[UPGRADED]' when the restaurant is upgraded.");
    }

    @Test
    public void testToStringDoesNotContainUpgradedLabelWhenNotUpgraded() {
        RestaurantReport report = new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR, false);
        assertFalse(report.toString().contains("[UPGRADED]"),
                "toString() should not contain '[UPGRADED]' when the restaurant is not upgraded.");
    }
}
