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

    }
}
