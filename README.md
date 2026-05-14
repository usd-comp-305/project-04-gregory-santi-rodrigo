# Restaurant Simulator

A multi-restaurant management simulation game built in Java. You play as a business owner managing a portfolio of restaurants over a 7-day week, making strategic decisions to grow your net worth from **$25,000** to **$50,000**.

---

## Description

The simulator supports 10 restaurant types: Burger, Pizza, Taco, Sushi, Hot Dog, Fine Dining, Cafe, Ice Cream, Juice Bar, and BBQ. Each restaurant has its own menu, operating hours, happy hour slot, and maximum daily order capacity. Some restaurants operate overnight, requiring special wraparound scheduling logic.

Each day, an order generator simulates customer demand using a weighted random system that skews orders toward rush hours (lunch and dinner) and each restaurant's happy hour, producing between 50 and 100 orders per day per restaurant. Between days, the player can upgrade restaurants, shut down underperformers, or shift happy hour timing to maximize revenue. The game ends in victory when net worth reaches the goal, or in defeat if it hits zero.

This project applies object-oriented design principles including abstraction, encapsulation, inheritance, and polymorphism, as well as the Strategy design pattern for pricing and test-driven development with JUnit 5 and Mockito.



## Prerequisites

- **Java 17** or higher
- **Gradle** (the project includes a Gradle wrapper — no separate install needed)
- **JUnit 5** (declared in `build.gradle` — pulled automatically on build)
- **Mockito** (declared in `build.gradle` — pulled automatically on build)



## Installation & Running

Clone the repository:

```bash
git clone https://github.com/usd-comp-305/project-04-gregory-santi-rodrigo
```

Navigate into the project folder:

```bash
cd project-04-gregory-santi-rodrigo
```

Build and Run the project:

```bash
./gradlew run
```


## Running the Tests

To run all tests:
```bash
./gradlew test
```



## License

This project was developed for educational purposes as part of COMP 305 at the University of San Diego.



## Contact

- Gregory — gmonzon@sandiego.edu
- Santiago — santiguerrero@sandiego.edu
- Rodrigo — rgarrido@sandiego.edu
