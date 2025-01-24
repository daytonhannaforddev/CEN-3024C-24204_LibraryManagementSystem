public class Patron {

    private int id;
    private String name;
    private String address;
    private Double fineAmount;


    Patron (int id, String name, String address, Double fineAmount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fineAmount = fineAmount;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getFineAmount() {
        return fineAmount;
    }

    public Double setFineAmount(Double fineAmount) {
        this.fineAmount = fineAmount;
        return fineAmount;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Address: " + address + ", Fine Amount: $" + fineAmount;
    }


}
