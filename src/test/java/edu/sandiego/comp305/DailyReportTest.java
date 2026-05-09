package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DailyReportTest {

    private static final int DAY_NUMBER = 1;

    private static final double REGULAR_REVENUE = 100.00;

    private static final double HAPPY_REVENUE = 40.00;

    private static final int PEAK_HOUR = 14;

    private RestaurantReport buildRestaurantReport(String name) {
        return new RestaurantReport(name, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR);
    }

    @Test
    public void testGetDayNumberReturnsCorrectDay(){

    }

    @Test
    public void testGetRestaurantReportsReturnsCorrectSize(){

    }
}
