import java.util.Scanner;

public class MtBullerAdmin {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        MtBullerResort resort = new MtBullerResort();

        boolean exitAdmin = false;

        System.out.println("\nPlease Enter an Option:\n"
        + "\n1). Display all accommodations"
        + "\n2). Display available accommodations"
        + "\n3). Add customer"
        + "\n4). List customers"
        + "\n5). Create a bundle"
        + "\n6). List bundles"
        + "\n7). Add a lift pass to bundle"
        + "\n8). Add lesson fees to bundle"
        + "\n9). Save bundles to file"
        + "\n10). Read bundles from file"
        + "\n11). Quit\n"
        );

        do {
            int optionEntered = scanner.nextInt();
            switch (optionEntered) {
                case 1:
                    resort.displayAllAccommodations();
                    break;
                case 2:
                    resort.displayAvailableAccommodations();
                    break;
                case 3:
                    resort.addCustomer();
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
        } while (!exitAdmin);

        scanner.close();
    }
}