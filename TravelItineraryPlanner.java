import java.util.ArrayList;
import java.util.Scanner;

class Trip {
    private String destination;
    private String startDate;
    private String endDate;
    private double estimatedBudget;

    public Trip(String destination, String startDate, String endDate, double estimatedBudget) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedBudget = estimatedBudget;
    }

    public String getDestination() {
        return destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getEstimatedBudget() {
        return estimatedBudget;
    }
}

public class TravelItineraryPlanner {
    private ArrayList<Trip> itinerary;

    public TravelItineraryPlanner() {
        this.itinerary = new ArrayList<>();
    }

    public void addTrip(String destination, String startDate, String endDate, double budget) {
        Trip newTrip = new Trip(destination, startDate, endDate, budget);
        itinerary.add(newTrip);
    }

    public void showItinerary() {
        if (itinerary.isEmpty()) {
            System.out.println("No trips added yet.");
            return;
        }

        System.out.println("\n--- Your Travel Itinerary ---");
        double totalBudget = 0;
        for (Trip trip : itinerary) {
            System.out.println("Destination: " + trip.getDestination());
            System.out.println("Start Date: " + trip.getStartDate());
            System.out.println("End Date: " + trip.getEndDate());
            System.out.printf("Estimated Budget: $%.2f\n", trip.getEstimatedBudget());
            totalBudget += trip.getEstimatedBudget();
            System.out.println("---------------------------");
        }
        System.out.printf("Total Estimated Budget for all trips: $%.2f\n", totalBudget);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelItineraryPlanner planner = new TravelItineraryPlanner();

        int choice;
        do {
            System.out.println("\n--- Travel Itinerary Planner Menu ---");
            System.out.println("1. Add a trip");
            System.out.println("2. Show itinerary");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter destination:");
                    String destination = scanner.nextLine();
                    System.out.println("Enter start date (yyyy-mm-dd):");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter end date (yyyy-mm-dd):");
                    String endDate = scanner.nextLine();
                    System.out.println("Enter estimated budget:");
                    double budget = scanner.nextDouble();
                    planner.addTrip(destination, startDate, endDate, budget);
                    System.out.println("Trip added successfully!");
                    break;
                case 2:
                    planner.showItinerary();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
