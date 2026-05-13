package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
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
        return new RestaurantReport(name, REGULAR_REVENUE, HAPPY_REVENUE, PEAK_HOUR, false);
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
        DailyReport report = new DailyReport(DAY_NUMBER, List.of());
        assertTrue(report.getRestaurantReports().isEmpty(),
                "getRestaurantReports() should be empty when no restaurants are passed in.");
    }

    @Test
    public void testGetRestaurantReportsContainsCorrectNames(){
        final int FROM_ZERO = 0;
        final int FROM_ONE = 1;

        List<RestaurantReport> reports = List.of(
                buildRestaurantReport("BBQ Shack"),
                buildRestaurantReport("Pizza Place")
        );
        DailyReport dailyReport = new DailyReport(DAY_NUMBER, reports);
        assertEquals("BBQ Shack", dailyReport.getRestaurantReports().get(FROM_ZERO).getRestaurantName());
        assertEquals("Pizza Place", dailyReport.getRestaurantReports().get(FROM_ONE).getRestaurantName());
    }

    @Test
    public void testGetTotalNetChangeEqualsSumOfAllRestaurantTotals(){
        final int DOUBLE = 2;

        List<RestaurantReport> reports = List.of(
                buildRestaurantReport("BBQ Shack"),
                buildRestaurantReport("Pizza Place")
        );
        DailyReport dailyReport = new DailyReport(DAY_NUMBER, reports);
        double expectedTotal = (REGULAR_REVENUE + HAPPY_REVENUE) * DOUBLE;
        assertEquals(expectedTotal, dailyReport.getTotalNetChange(), THOUSANDTH_DECIMAL,
                "getTotalNetChange() should equal the sum of all restaurant totals.");
    }

    @Test
    public void testGetTotalNetChangeIsZeroWithNoRestaurants(){
        DailyReport report = new DailyReport(DAY_NUMBER, List.of());
        assertEquals(ZERO_REVENUE, report.getTotalNetChange(), THOUSANDTH_DECIMAL,
                "getTotalNetChange() should be zero when there are no restaurants.");
    }

    @Test
    public void testGetTotalNetChangeIsNegativeWhenRestaurantsLoseMoney(){
        final double NEGATIVE_REVENUE = -200.0;
        final double SECOND_NEGATIVE_REVENUE = -150.0;
        final int GREATER_THAN_ZERO = 0;

        List<RestaurantReport> reports = List.of(
                new RestaurantReport("BBQ Shack", NEGATIVE_REVENUE, ZERO_REVENUE, PEAK_HOUR, false),
                new RestaurantReport("Pizza Place", SECOND_NEGATIVE_REVENUE, ZERO_REVENUE, PEAK_HOUR, false)
        );
        DailyReport dailyReport = new DailyReport(DAY_NUMBER, reports);
        assertTrue(dailyReport.getTotalNetChange() < GREATER_THAN_ZERO,
                "getTotalNetChange() should be negative when all restaurants lost money.");
    }

    @Test
    public void testGetTotalNetChangeWithMixedProfitAndLoss(){
        final double POS_REVENUE = 500.0;
        final double NEG_REVENUE = -200.0;
        final double PROFIT = 300.0;
        List<RestaurantReport> reports = List.of(
                new RestaurantReport("BBQ Shack",    POS_REVENUE, ZERO_REVENUE, PEAK_HOUR, false),
                new RestaurantReport("Pizza Place", NEG_REVENUE, ZERO_REVENUE, PEAK_HOUR, false)
        );
        DailyReport dailyReport = new DailyReport(DAY_NUMBER, reports);
        assertEquals(PROFIT, dailyReport.getTotalNetChange(), THOUSANDTH_DECIMAL,
                "getTotalNetChange() should correctly net a profit and a loss.");
    }
}
