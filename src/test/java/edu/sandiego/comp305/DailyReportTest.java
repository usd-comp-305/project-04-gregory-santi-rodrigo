package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DailyReportTest {

    private static final int DAY_NUMBER = 1;

    private static final double REGULAR_REVENUE = 100.00;

    private static final double HAPPY_REVENUE = 40.00;

    private static final double ZERO_REVENUE = 0.0;

    private static final double THOUSANDTH_DECIMAL = 0.001;

    private static final int PEAK_HOUR = 14;

    private RestaurantReport buildRestaurantReport(String name) {
        return new RestaurantReport(name, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR);
    }

    @Test
    public void testGetDayNumberReturnsCorrectDay(){
        DailyReport report = new DailyReport(DAY_NUMBER, List.of(buildRestaurantReport("BBQ Shack")));
        assertEquals(DAY_NUMBER, report.getDayNumber(),
                "getDayNumber() should return the day number passed to the constructor.");
    }

    @Test
    public void testGetRestaurantReportsReturnsCorrectSize(){
        final int RESTAURANT_COUNT = 2;

        List<RestaurantReport> reports = List.of(
                buildRestaurantReport("BBQ Shack"),
                buildRestaurantReport("Pizza Place")
        );
        DailyReport dailyReport = new DailyReport(DAY_NUMBER, reports);
        assertEquals(RESTAURANT_COUNT, dailyReport.getRestaurantReports().size(),
                "getRestaurantReports() should return all restaurant reports.");
    }

    @Test
    public void testGetRestaurantReportsIsEmptyWithNoRestaurants(){

    }

    @Test
    public void testGetRestaurantReportsContainsCorrectNames(){

    }

    @Test
    public void testGetTotalNetChangeEqualsSumOfAllRestaurantTotals(){

    }

    @Test
    public void testGetTotalNetChangeIsZeroWithNoRestaurants(){

    }

    @Test
    public void testGetTotalNetChangeIsNegativeWhenRestaurantsLoseMoney(){

    }

    @Test
    public void testGetTotalNetChangeWithMixedProfitAndLoss(){
        final double POS_REVENUE = 500.0;
        final double NEG_REVENUE = -200.0;
        final double PROFIT = 300.0;
        List<RestaurantReport> reports = List.of(
                new RestaurantReport("BBQ Shack",    POS_REVENUE, ZERO_REVENUE, PEAK_HOUR),
                new RestaurantReport("Pizza Place", NEG_REVENUE, ZERO_REVENUE, PEAK_HOUR)
        );
        DailyReport dailyReport = new DailyReport(DAY_NUMBER, reports);
        assertEquals(PROFIT, dailyReport.getTotalNetChange(), THOUSANDTH_DECIMAL,
                "getTotalNetChange() should correctly net a profit and a loss.");
    }
}
