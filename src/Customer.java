import java.io.Serializable;

public class Customer implements Serializable {

    private SkiingLevel level;

    private String name;
    private int id;

    public Customer(int id, String name, SkiingLevel level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public SkiingLevel getLevel() {
        return level;
    }

    public void setLevel(SkiingLevel level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
        "------------------------------------------------------------"
        + "\n                      Customer Details                      "
        + "\n------------------------------------------------------------"
        + "\nCustomer ID: " + id
        + "\nCustomer Name: " + name
        + "\nCustomer Ski Level: " + level;
    }
}