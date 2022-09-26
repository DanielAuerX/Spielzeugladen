package toy_features;

public class Address {
    private String street;
    private String houseNumber;
    private int zipCode;
    private String city;

    public Address(String street, String houseNumber, int zipCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return street + " " + houseNumber + ", " + zipCode + " " + city;
    }
}
