import accommodation.Accommodation;

public class TravelBundle {

    private Customer customer;
    private Accommodation accommodation;
    private LiftPassType liftPass;
    private int accommodationNights;
    private int liftPassDays;
    private int numberofLessons;

    public TravelBundle(Customer customer, Accommodation accommodation, LiftPassType liftPass, int accommodationNights, int liftPassDays, int numberofLessons) {
        this.customer = customer;
        this.accommodation = accommodation;
        this.liftPass = liftPass;
        this.accommodationNights = accommodationNights;
        this.liftPassDays = liftPassDays;
        this.numberofLessons = numberofLessons;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public LiftPassType getLiftPass() {
        return liftPass;
    }

    public void setLiftPass(LiftPassType liftPass) {
        this.liftPass = liftPass;
    }

    public int getAccommodationNights() {
        return accommodationNights;
    }

    public void setAccommodationNights(int accommodationNights) {
        this.accommodationNights = accommodationNights;
    }

    public int getLiftPassDays() {
        return liftPassDays;
    }

    public void setLiftPassDays(int liftPassDays) {
        this.liftPassDays = liftPassDays;
    }

    public int getNumberofLessons() {
        return numberofLessons;
    }

    public void setNumberofLessons(int numberofLessons) {
        this.numberofLessons = numberofLessons;
    }

    public double calculateBundlePrice() {

        // Accommodation
        double accommodationCost = accommodation.getPrice() * accommodationNights;

        // Lift Pass
        double liftPassCost = 0.0;

        switch (liftPass) {
            case SINGLE_DAY:
                liftPassCost = 26.0;
                liftPassCost = liftPassCost * liftPassDays;
                break;
            case FIVE_DAYS:
                liftPassCost = 26.0 * 0.90;
                liftPassCost = liftPassCost * liftPassDays;
                break;
            case SEASON:
                liftPassCost = 200.0;
                break;
            case NONE:
                liftPassCost = 0.0;
                break;
        }

        // Lessons
        double lessonFee = 0.0;

        switch (customer.getLevel()) {
            case BEGINNER:
                lessonFee = 25.0;
                break;
            case INTERMEDIATE:
                lessonFee = 20.0;
                break;
            case EXPERT:
                lessonFee = 15.0;
                break;
        }

        double totalLessonCost = lessonFee * numberofLessons;

        // Final Calculation
        double totalBundlePrice = accommodationCost + liftPassCost + totalLessonCost;
        return totalBundlePrice;
    }

    @Override
    public String toString() {
        return "--------------------------------"
        + "\nTravel Bundle Details:"
        + "\n" + customer
        + "\n" + accommodation
        + "\nLift Pass: " + liftPass
        + "\nLift Pass Days: " + liftPassDays
        + "\nNumber of Lessons: " + numberofLessons
        + "\nTotal Bundle Price: $" + calculateBundlePrice();
    }
}