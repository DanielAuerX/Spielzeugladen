package toy_features;

public enum StorageLocation {
    LOCATION1("Hafenhalle"), LOCATION2("Steindamm-Keller"), LOCATION3("Lagerhalle-Elmshorn");

    private final String locationName;

    StorageLocation(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
