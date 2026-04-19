import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

import accommodation.Accommodation;
import accommodation.Pricable;

public class TravelBundle implements Pricable, Serializable {

    private Customer customer;
    private Accommodation accommodation;
    private LiftPassType liftPass;
    private LocalDate startDate;
    private int accommodationNights;
    private int liftPassDays;
    private int numberofLessons;

    public TravelBundle(Customer customer, Accommodation accommodation, LiftPassType liftPass, LocalDate startDate, int accommodationNights, int liftPassDays, int numberofLessons) {
        this.customer = customer;
        this.accommodation = accommodation;
        this.liftPass = liftPass;
        this.startDate = startDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public double getPrice() {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate;

        if (startDate != null) {
            formattedDate = startDate.format(formatter);
        } else {
            formattedDate = "Not Set";
        }

        return
        "\n------------------------------------------------------------"
        + "\n                   Travel Bundle Details                    "
        + "\n------------------------------------------------------------"
        + "\n" + customer
        + "\n------------------------------------------------------------"
        + "\n                       Bundle Summary                       "
        + "\n------------------------------------------------------------"
        + "\nStart Date:        " + formattedDate
        + "\nAccommodation:     " + accommodation.getType() + " (ID: " + accommodation.getId() + ")"
        + "\nDuration:          " + accommodationNights + " nights"
        + "\nLift Pass:         " + liftPass + " (" + liftPassDays + " days)"
        + "\nSki Lessons:       " + numberofLessons
        + "\n------------------------------------------------------------"
        + "\nTotal Bundle Price: $" + getPrice();
    }
}