package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantReportTest {

    private static final String TEST_NAME = "BBQ Shack";

    private static final double REGULAR_REVENUE = 100.00;

    private static final double HAPPY_REVENUE = 40.00;

    private static final double ZERO_REVENUE = 0.0;

    private static final int PEAK_HOUR = 14;

    private static final double THOUSANDTH_DECIMAL = 0.001;

    private RestaurantReport buildReport(){
        return new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR);
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
        RestaurantReport report = new RestaurantReport(TEST_NAME, REGULAR_REVENUE, ZERO_REVENUE, PEAK_HOUR);
        assertEquals(REGULAR_REVENUE, report.getTotalRevenue(), THOUSANDTH_DECIMAL,
                "getTotalRevenue() should equal regular revenue when happy hour is zero.");
    }

    @Test
    public void testGetTotalRevenueWithZeroRegular(){
        RestaurantReport report = new RestaurantReport(TEST_NAME, ZERO_REVENUE, HAPPY_REVENUE, PEAK_HOUR);
        assertEquals(HAPPY_REVENUE, report.getTotalRevenue(), THOUSANDTH_DECIMAL,
                "getTotalRevenue() should equal happy hour revenue when regular is zero.");
    }

    @Test
    public void testGetTotalRevenueWithBothZero(){

    }

    @Test
    public void testGetTotalRevenueIsNegativeWhenRevenueIsNegative(){

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
}
