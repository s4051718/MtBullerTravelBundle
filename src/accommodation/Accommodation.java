package accommodation;

import java.io.Serializable;

public abstract class Accommodation implements Pricable, Serializable {

    String id;
    String type;
    double price;
    boolean available = true;

    public Accommodation(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return
        "------------------------------------------------------------"
        + "\n                   Accommodation Details                    "
        + "\n------------------------------------------------------------"
        + "\nAccommodation ID: " + id
        + "\nAccommodation Type: " + type
        + "\nAccommodation Price: $" + price + " per night";
    }
}