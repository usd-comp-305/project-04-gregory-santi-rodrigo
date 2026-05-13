package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

public class Simulator {

    //  Game constants
    private static final double STARTING_NET_WORTH = 25000;
    private static final double GOAL_NET_WORTH = 50000;
    private static final double STARTUP_COST_PER_RESTAURANT = 1000;
    private static final int MIN_RESTAURANTS = 5;
    private static final int MAX_RESTAURANTS = 10;
    private static final int DAYS_IN_WEEK = 7;

    // Instance fields
    private final List<Restaurant> restaurants;
    private final OrderGenerator orderGenerator;
    private int currentDay;
    private List<Order> lastGeneratedOrders;


    public Simulator(List<Restaurant> restaurants, OrderGenerator orderGenerator){
        this.restaurants = restaurants;
        this.orderGenerator = orderGenerator;
        this.currentDay = 1;
        this.lastGeneratedOrders = new ArrayList<>();
    }

    public DailyReport runDay() {
        List<RestaurantReport> reports = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            restaurant.resetDay();

            lastGeneratedOrders = orderGenerator.generateDailyOrders(restaurant);
            List<Order> orders = lastGeneratedOrders;
            int allowedOrders = Math.min(orders.size(), restaurant.getMaxOrdersPerDay());

            for (int orderCount = 0; orderCount < allowedOrders; orderCount++) {
                restaurant.processOrder(orders.get(orderCount));
            }

            int peakHour = computePeakHour(orders.subList(0, allowedOrders));
            reports.add(restaurant.generateReport(peakHour));
        }

        DailyReport dailyReport = new DailyReport(currentDay, reports);
        currentDay++;
        return dailyReport;
    }

    int computePeakHour(List<Order> orders) {
        int[] hourCounts = new int[24];
        for (Order order : orders) {
            hourCounts[order.getHour()]++;
        }

        int peakHour = 0;
        for (int hour = 1; hour < 24; hour++) {
            if (hourCounts[hour] > hourCounts[peakHour]) {
                peakHour = hour;
            }
        }

        return peakHour;
    }

    List<Order> getLastGeneratedOrders(){
        return lastGeneratedOrders;
    }

    public int getCurrentDay() { return currentDay; }

    // game entry point
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Single Random instance shared across the entire program
        Random random = new Random();

        // Build pool of all 10 restaurants
        List<Restaurant> allRestaurants = new ArrayList<>(List.of(
                new BurgerRestaurant(),
                new PizzaRestaurant(),
                new TacoRestaurant(),
                new SushiRestaurant(),
                new HotDogRestaurant(),
                new FineDiningRestaurant(),
                new CafeRestaurant(),
                new IceCreamRestaurant(),
                new JuiceRestaurant(),
                new BBQRestaurant()
        ));

        // Prompt user for number of restaurants
        int numRestaurants = 0;
        while (numRestaurants < MIN_RESTAURANTS || numRestaurants > MAX_RESTAURANTS) {
            System.out.print("How many restaurants do you want to manage? (5-10): ");
            try {
                numRestaurants = Integer.parseInt(scanner.nextLine().trim());
                if (numRestaurants < MIN_RESTAURANTS || numRestaurants > MAX_RESTAURANTS) {
                    System.out.println("Please enter a number between 5 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 5 and 10.");
            }
        }

        // Randomly select n restaurants
        Collections.shuffle(allRestaurants, random);
        List<Restaurant> selectedRestaurants = allRestaurants.subList(0, numRestaurants);

        // Deduct startup costs
        double startupCosts = numRestaurants * STARTUP_COST_PER_RESTAURANT;
        Owner owner = new Owner(STARTING_NET_WORTH - startupCosts, GOAL_NET_WORTH, selectedRestaurants);

        System.out.println("\nYou are managing the following restaurants:");
        for (Restaurant r : selectedRestaurants) {
            System.out.println("  - " + r.getName());
        }
        System.out.printf("Starting net worth after startup costs: $%.2f%n", owner.getNetWorth());
        System.out.println("Goal: reach $" + (int) GOAL_NET_WORTH + " in " + DAYS_IN_WEEK + " days.\n");

        // Game loop
        Simulator simulator = new Simulator(owner.getRestaurants(), new OrderGenerator(random));

        for (int day = 1; day <= DAYS_IN_WEEK; day++) {
            System.out.println("========================================");
            System.out.println("DAY " + day);
            System.out.println("========================================");

            DailyReport report = simulator.runDay();

            // Calculate and apply profit
            owner.applyDailyReport(report);

            // Print report
            System.out.println("\nDAILY REPORT:");
            for (RestaurantReport rr : report.getRestaurantReports()) {
                System.out.println(rr);
            }
            System.out.printf("%nCurrent net worth: $%.2f / $%.2f%n", owner.getNetWorth(), GOAL_NET_WORTH);

            // Check win/loss
            if (owner.hasWon()) {
                System.out.println("\nCongratulations! You reached $" + (int) GOAL_NET_WORTH + "! You win!");
                scanner.close();
                return;
            }
            if (owner.hasLost()) {
                System.out.println("\nYou ran out of money. Game over!");
                scanner.close();
                return;
            }

            if (day == DAYS_IN_WEEK) break;

            // User actions between days
            System.out.println("\nDAY " + day + " is over. What would you like to do?");
            boolean continueToNextDay = false;
            while (!continueToNextDay) {
                System.out.println("\n1) Upgrade a restaurant ($1000)");
                System.out.println("2) Shut down a restaurant permanently");
                System.out.println("3) Change happy hour of a restaurant");
                System.out.println("4) Continue to day " + (day + 1));
                System.out.print("Choose an option (1-4): ");

                String input = scanner.nextLine().trim();
                switch (input) {
                    case "1" -> {
                        List<Restaurant> active = owner.getRestaurants();
                        System.out.println("Select a restaurant to upgrade:");
                        for (int i = 0; i < active.size(); i++) {
                            String upgradeStatus = active.get(i).isUpgraded() ? " (already upgraded)" : "";
                            System.out.println("  " + (i + 1) + ". " + active.get(i).getName() + upgradeStatus);
                        }
                        try {
                            int choice = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            if (choice >= 0 && choice < active.size()) {
                                owner.upgrade(active.get(choice));
                            } else {
                                System.out.println("Invalid selection, no change made.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input, no change made.");
                        }
                    }
                    case "2" -> {
                        List<Restaurant> active = owner.getRestaurants();
                        if (active.size() == 1) {
                            System.out.println("You can't shut down your last restaurant!");
                        } else {
                            System.out.println("Select a restaurant to shut down:");
                            for (int i = 0; i < active.size(); i++) {
                                System.out.println("  " + (i + 1) + ". " + active.get(i).getName());
                            }
                            try {
                                int choice = Integer.parseInt(scanner.nextLine().trim()) - 1;
                                if (choice >= 0 && choice < active.size()) {
                                    Restaurant toShutdown = active.get(choice);
                                    owner.shutdownRestaurant(toShutdown);
                                    System.out.println(toShutdown.getName() + " has been shut down.");
                                } else {
                                    System.out.println("Invalid selection, no change made.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input, no change made.");
                            }
                        }
                    }
                    case "3" -> {
                        List<Restaurant> active = owner.getRestaurants();
                        System.out.println("Select a restaurant:");
                        for (int i = 0; i < active.size(); i++) {
                            System.out.println("  " + (i + 1) + ". " + active.get(i).getName()
                                    + " (current happy hour: " + active.get(i).getHappyHourStart() + ":00)");
                        }
                        try {
                            int choice = Integer.parseInt(scanner.nextLine().trim()) - 1;
                            if (choice >= 0 && choice < active.size()) {
                                System.out.print("Enter new happy hour (0-23): ");
                                int newHour = Integer.parseInt(scanner.nextLine().trim());
                                if (newHour >= 0 && newHour <= 23) {
                                    owner.changeHappyHour(active.get(choice), newHour);
                                    System.out.println("Happy hour updated to " + newHour + ":00.");
                                } else {
                                    System.out.println("Invalid hour, no change made.");
                                }
                            } else {
                                System.out.println("Invalid selection, no change made.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input, no change made.");
                        }
                    }
                    case "4" -> continueToNextDay = true;
                    default -> System.out.println("Invalid option, please choose 1-4.");
                }
            }
        }

        // End of week
        System.out.println("========================================");
        System.out.println("GAME OVER - End of week summary");
        System.out.printf("Final net worth: $%.2f%n", owner.getNetWorth());
        if (owner.hasWon()) {
            System.out.println("You reached your goal! You win!");
        } else {
            System.out.println("You didn't reach $" + (int) GOAL_NET_WORTH + ". Better luck next time!");
        }

        scanner.close();
    }
}