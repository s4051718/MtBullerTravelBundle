import java.util.Scanner;
import java.util.ArrayList;

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

    }

    public void displayAvailableAccommodations() {

    }

    public void addCustomer() {

    }

    public void listCustomers() {

    }

    public void createTravelBundle() {

    }

    public void listTravelBundles() {

    }

    public void addLiftPassToBundle() {

    }

    public void addLessonToBundle() {

    }

    public void writeBundleToFile() {

    }

    public void readBundleFromFile() {

    }
}