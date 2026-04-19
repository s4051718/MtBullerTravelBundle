import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import accommodation.Accommodation;
import accommodation.Apartment;
import accommodation.HotelRoom;
import accommodation.LodgeRoom;

public class MtBullerResort {

    Scanner scanner = new Scanner(System.in);

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private ArrayList<TravelBundle> travelBundles = new ArrayList<>();

    public MtBullerResort() {

        // Populate the customers ArrayList
        Customer c1 = new Customer(1, "Alice", SkiingLevel.BEGINNER);
        Customer c2 = new Customer(2, "Bob", SkiingLevel.INTERMEDIATE);
        Customer c3 = new Customer(3, "Charlie", SkiingLevel.EXPERT);

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        // Populate the accommodations ArrayList
        LodgeRoom l1LodgeRoom = new LodgeRoom("l1", 150);
        LodgeRoom l2LodgeRoom = new LodgeRoom("l2", 150);
        LodgeRoom l3LodgeRoom = new LodgeRoom("l3", 150);
        LodgeRoom l4LodgeRoom = new LodgeRoom("l4", 150);
        Apartment a1Apartment = new Apartment("a1", 120);
        Apartment a2Apartment = new Apartment("a2", 120);
        Apartment a3Apartment = new Apartment("a3", 120);
        Apartment a4Apartment = new Apartment("a4", 120);
        HotelRoom h1HotelRoom = new HotelRoom("h1", 100);
        HotelRoom h2HotelRoom = new HotelRoom("h2", 100);
        HotelRoom h3HotelRoom = new HotelRoom("h3", 100);
        HotelRoom h4HotelRoom = new HotelRoom("h4", 100);


        accommodations.add(l1LodgeRoom);
        accommodations.add(l2LodgeRoom);
        accommodations.add(l3LodgeRoom);
        accommodations.add(l4LodgeRoom);
        accommodations.add(a1Apartment);
        accommodations.add(a2Apartment);
        accommodations.add(a3Apartment);
        accommodations.add(a4Apartment);
        accommodations.add(h1HotelRoom);
        accommodations.add(h2HotelRoom);
        accommodations.add(h3HotelRoom);
        accommodations.add(h4HotelRoom);
    }

    public void displayAllAccommodations() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                     All Accommodations                     ");
        System.out.println("------------------------------------------------------------");

        for (Accommodation room : accommodations) {
            String availability;
            if (room.isAvailable()) {
                availability = "Available";
            } else {
                availability = "Booked";
            }
            System.out.println("ID: " + room.getId() + " | Type: " + room.getType() + " | Price: $" + room.getPrice() + " | Status: " + availability);
        }
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void displayAvailableAccommodations() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                  Available Accommodations                  ");
        System.out.println("------------------------------------------------------------");

        for (Accommodation room : accommodations) {
            if (room.isAvailable()) {
                System.out.println("ID: " + room.getId() + " | Type: " + room.getType() + " | Price: $" + room.getPrice());
            }
        }
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public Customer addCustomer() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                Create New Customer Account                 ");
        System.out.println("------------------------------------------------------------");

        boolean validId = true;
        int newId = 0;

        System.out.println("Please enter a new ID for the customer: ");
        do {
            try {
                newId = scanner.nextInt();
                scanner.nextLine();
                if (newId <= 0) {
                    System.out.println("Invalid ID. Please enter a positive number.");
                } else if (newId >= 1000) {
                    System.out.println("Invalid ID. Please enter a number less than 1000.");
                } else {
                    validId = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        } while (validId);

        System.out.println("\nPlease enter the customers name: ");
        String newName = scanner.nextLine();

        boolean validLevel = true;
        int levelChoice = 0;
        SkiingLevel skiLevel = null;

        System.out.println("\nPlease enter the customers skiing level: "
        + "\n1). Beginner"
        + "\n2). Intermediate"
        + "\n3). Expert"
        );
        do {
            try {
                levelChoice = scanner.nextInt();
                scanner.nextLine();
                if (levelChoice < 1 || levelChoice > 3) {
                    System.out.println("Invalid level. Please enter a number between 1 and 3.");
                } else {
                    validLevel = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
            switch (levelChoice) {
                case 1:
                    skiLevel = SkiingLevel.BEGINNER;
                    break;
                case 2:
                    skiLevel = SkiingLevel.INTERMEDIATE;
                    break;
                case 3:
                    skiLevel = SkiingLevel.EXPERT;
                    break;
                default:
                    break;
            }
        } while (validLevel);

        Customer customer = new Customer(newId, newName, skiLevel);

        customers.add(customer);
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                      Customer Details                      ");
        System.out.println("------------------------------------------------------------");
        System.out.println("ID: " + customer.getId() + " | Name: " + customer.getName() + " | Level: " + customer.getLevel());
        System.out.println("------------------------------------------------------------");
        return customer;
    }

    public void listCustomers() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                     List of Customers                      ");
        System.out.println("------------------------------------------------------------");

        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getId() + " | Name: " + customer.getName() + " | Level: " + customer.getLevel());
        }
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void createTravelBundle() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                    Create Travel Bundle                    ");
        System.out.println("------------------------------------------------------------");

        // Customer Selection
        Customer selectedCustomer = null;

        while (selectedCustomer == null) {
            try {
                System.out.println("Is the customer:"
                + "\n1). New "
                + "\n2). Current");
                int customerType = scanner.nextInt();
                scanner.nextLine();

                switch (customerType) {
                    case 1:
                        selectedCustomer = addCustomer();
                        break;
                    case 2:
                        System.out.println("\nFind by:"
                        + "\n1). Customer ID"
                        + "\n2). Customer Name");
                        int selection = scanner.nextInt();
                        scanner.nextLine();
                        switch (selection) {
                            case 1:
                                System.out.println("\nPlease enter the customers ID number: ");
                                int targetId = scanner.nextInt();
                                scanner.nextLine();
                                for (Customer customer : customers) {
                                    if (customer.getId() == targetId) {
                                        selectedCustomer = customer;
                                        System.out.println("\nCustomer found: " + customer.getName());
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("\nPlease enter the customers name: ");
                                String targetName = scanner.nextLine().trim();
                                selectedCustomer = findCustomerByName(targetName);
                                break;
                            default:
                                System.out.println("Invalid selection. Please enter '1' or '2'.");
                                break;
                        }
                        if (selectedCustomer == null) {
                            throw new MtBullerException("Customer not found. Please try again or create a new account.");
                        }
                        break;
                    default:
                        System.out.println("Invalid selection. Please enter '1' or '2'.");
                        break;
                }
            } catch (MtBullerException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        // Accommodation Selection
        Accommodation selectedAccommodation = null;
        int accommodationNights = 0;

        while (selectedAccommodation == null) {
            System.out.println("\n------------------------------------------------------------");
            System.out.println("                 Available Accommodations                  ");
            System.out.println("------------------------------------------------------------");
            for (Accommodation room : accommodations) {
                if (room.isAvailable()) {
                    System.out.println("ID: " + room.getId() + " | Type: " + room.getType() + " | Price: $" + room.getPrice());
                }
            }
            System.out.println("------------------------------------------------------------");

            System.out.println("\nPlease enter an Accommodation ID from the list above: ");
            String targetAccommodationId = scanner.nextLine();

            for (Accommodation currentAccommodation : accommodations) {
                if (currentAccommodation.getId().equals(targetAccommodationId)) {
                    selectedAccommodation = currentAccommodation;
                }
            }
            if (selectedAccommodation == null) {
                System.out.println("\nAccommodation ID not found. Please choose from the list.");
            }
        }

        LocalDate startDate = null;

        while (startDate == null) {
            try {
                System.out.println("\nWhat date begins the stay (dd/MM/yyyy)?");
                String dateString = scanner.nextLine().trim();

                if (dateString.isEmpty()) {
                    continue;
                }

                startDate = parseIssueDate(dateString);

                if (startDate.isBefore(LocalDate.now())) {
                    startDate = null; // Reset to keep looping
                    throw new MtBullerException("Booking date cannot be in the past.");
                }

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy (e.g., 25/12/2026).");
            } catch (MtBullerException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        int passDays = 0;
        int lessons = 0;

        while (true) {
            try {
                System.out.println("\nHow many nights accommodation?: ");
                accommodationNights = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter numbers.");
                scanner.nextLine();
            }
        }

        LiftPassType passType = null;

        while (true) {
            try {
                System.out.println("\nHow many days lift access?: ");
                passDays = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter numbers.");
                scanner.nextLine();
            }
        }

        if (passDays == 0) {
            passType = LiftPassType.NONE;
        } else if (passDays >= 30) {
            passType = LiftPassType.SEASON;
        } else if (passDays >= 5) {
            passType = LiftPassType.FIVE_DAYS;
        } else {
            passType = LiftPassType.SINGLE_DAY;
        }

        while (true) {
            try {
                System.out.println("\nHow many ski lessons? ");
                lessons = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter numbers.");
                scanner.nextLine();
            }
        }

        // Bundle Creation
        TravelBundle newBundle = new TravelBundle(selectedCustomer, selectedAccommodation, passType, startDate, accommodationNights, passDays, lessons);

        travelBundles.add(newBundle);
        selectedAccommodation.setAvailable(false);

        // Display Bundle
        System.out.println("\n" + newBundle);
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void listTravelBundles() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                   List of Travel Bundles                   ");
        System.out.println("------------------------------------------------------------");

        for (TravelBundle bundle : travelBundles) {
            System.out.println(bundle);
        }
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void addLiftPassToBundle() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                  Add Lift Pass To Bundle                   ");
        System.out.println("------------------------------------------------------------");

        System.out.println("Please enter the customers ID number: ");
        int targetCustomerId = scanner.nextInt();
        scanner.nextLine();

        TravelBundle selectedTravelBundle = null;

        for (TravelBundle bundle : travelBundles) {
            if (bundle.getCustomer().getId() == targetCustomerId) {
                selectedTravelBundle = bundle;
                break;
            }
        }

        if (selectedTravelBundle != null) {
            System.out.println("\nHow many days lift access?: ");
            int passDays = scanner.nextInt();
            scanner.nextLine();

            LiftPassType passType = null;

            if (passDays == 0) {
                passType = LiftPassType.NONE;
            } else if (passDays >= 30) {
                passType = LiftPassType.SEASON;
            } else if (passDays >= 5) {
                passType = LiftPassType.FIVE_DAYS;
            } else {
                passType = LiftPassType.SINGLE_DAY;
            }

            selectedTravelBundle.setLiftPass(passType);
            selectedTravelBundle.setLiftPassDays(passDays);
        } else {
            System.out.println("Bundle not found for Customer ID: " + targetCustomerId);
        }
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void addLessonToBundle() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                    Add Lessons To Bundle                    ");
        System.out.println("------------------------------------------------------------");

       System.out.println("Please enter the customers ID number: ");
        int targetCustomerId = scanner.nextInt();
        scanner.nextLine();

        TravelBundle selectedTravelBundle = null;

        for (TravelBundle bundle : travelBundles) {
            if (bundle.getCustomer().getId() == targetCustomerId) {
                selectedTravelBundle = bundle;
                break;
            }
        }

        if (selectedTravelBundle != null) {
            System.out.println("How many lessons? ");
            int lessons = scanner.nextInt();
            scanner.nextLine();
            selectedTravelBundle.setNumberofLessons(selectedTravelBundle.getNumberofLessons() + lessons);
        } else {
            System.out.println("Bundle not found for Customer ID: " + targetCustomerId);
        }
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void writeBundleToFile() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                    Write Bundle To File                    ");
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    public void readBundleFromFile() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                   Read Bundle From File                    ");
        System.out.println("------------------------------------------------------------");
        pauseForUser();
    }

    // Helper Methods
    public void pauseForUser() {
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }

    private Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    public LocalDate parseIssueDate(String input) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(input, formatter);
        }
}