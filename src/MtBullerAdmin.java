import java.util.InputMismatchException;
import java.util.Scanner;

public class MtBullerAdmin {

    static Scanner scanner = new Scanner(System.in);
    static MtBullerResort resort = new MtBullerResort();
    static boolean exitAdmin = false;
    public static void main(String[] args) throws Exception {
        do {
            menu();
        } while (!exitAdmin);
    }

    public static void menu() {
        int optionEntered = 0;

        System.out.println("\n------------------------------------------------------------");
        System.out.println("                   Mt Buller Resort Admin                   ");
        System.out.println("------------------------------------------------------------");
        System.out.println(
        "\nPlease Enter an Option:\n"
        + "\n1).  Display all accommodations"
        + "\n2).  Display available accommodations"
        + "\n3).  Add customer"
        + "\n4).  List customers"
        + "\n5).  Create a bundle"
        + "\n6).  List bundles"
        + "\n7).  Add a lift pass to bundle"
        + "\n8).  Add lessons to bundle"
        + "\n9).  Save bundles to file"
        + "\n10). Read bundles from file"
        + "\n11). Quit\n"
        );
        System.out.println("------------------------------------------------------------\n");
        try {
            optionEntered = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }
        switch (optionEntered) {
            case 1:
                resort.displayAllAccommodations();
                break;
            case 2:
                resort.displayAvailableAccommodations();
                break;
            case 3:
                resort.addCustomer();
                resort.pauseForUser();
                break;
            case 4:
                resort.listCustomers();
                break;
            case 5:
                resort.createTravelBundle();
                break;
            case 6:
                resort.listTravelBundles();
                break;
            case 7:
                resort.addLiftPassToBundle();
                break;
            case 8:
                resort.addLessonToBundle();
                break;
            case 9:
                resort.writeBundleToFile();
                break;
            case 10:
                resort.readBundleFromFile();
                break;
            case 11:
                exitAdmin = true;
                break;
            default:
                break;
        }
    }
}