package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantReportTest {

    private static final String TEST_NAME = "BBQ Shack";

    private static final double REGULAR_REVENUE = 100.00;

    private static final double HAPPY_REVENUE = 40.00;

    private static final int PEAK_HOUR = 14;

    private static final double THOUSANDTH_DECIMAL = 0.001;

    private RestaurantReport buildReport(){
        return new RestaurantReport(TEST_NAME, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR);
    }

    @Test
    public void testGetRestaurantNameReturnsCorrectName(){

    }

    @Test
    public void testGetRegularRevenueReturnsCorrectValue(){

    }

    @Test
    public void testGetHappyHourRevenueReturnsCorrectValue(){

    }

    @Test
    public void testGetTotalRevenueIsRegularPlusHappyHour(){

    }

    @Test
    public void testGetTotalRevenueWithZeroHappyHour(){

    }
}
