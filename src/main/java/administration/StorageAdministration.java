package administration;

import data.JsonIO;
import data.Repository;
import toy_features.StorageLocation;
import toys.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static administration.ToyAdministration.BOLD;
import static administration.ToyAdministration.RESET;

public class StorageAdministration {
    // get toys by storage location (done)
    // change storage location of a toy (done)
    // set default storage location
    // quit program

    private final Transformer transformer = new Transformer();
    private final UserInterface userInterface = new UserInterface();
    private final Repository repository = new Repository();
    private final Finder finder = new Finder();
    private final JsonIO jsonIO = new JsonIO();

    public String find() {
        ArrayList<Vehicle> vehicles = finder.findByLocation();
        String resultList = "";
        if (vehicles.isEmpty()) {
            resultList = "Es sind derzeit keine Artikel mit diesem Merkmal auf Lager.";
        } else {
            int counter = 1;
            resultList += "Folgende Artikel wurden gefunden:\n";
            for (Vehicle vehicle : vehicles.stream().sorted(Comparator.comparing(Vehicle::getExternalId)).toList()) {
                resultList += counter + ") " + "Artikelnummer " + vehicle.getExternalId() +
                        "\n\t" + " " + vehicle.getName() + " (" + "Lieferdatum: " + transformer.dateToString(vehicle.getDeliveryDate()) + ")\n";
                counter++;
            }
        }
        return resultList;
    }

    public void changeLocation(){
        Vehicle vehicle = getVehicle();
        Vehicle updatedVehicle = updateLocation(vehicle);
        jsonIO.writeVehicleToData(updatedVehicle, repository.getConfig().getInventoryPath());
    }

    private Vehicle updateLocation(Vehicle vehicle) {
        ArrayList<StorageLocation> allLocations = new ArrayList<>(Arrays.asList(StorageLocation.LOCATION1, StorageLocation.LOCATION2, StorageLocation.LOCATION3));
        allLocations.remove(vehicle.getStorageLocation());
        String locationText = "Der Artikel befindet sich in " + vehicle.getStorageLocation().getLocationName() +
                ".\nWo soll der Artikel gelagert werden?\n" +
                "1\t=\t" + allLocations.get(0).getLocationName() +
                "\n2\t=\t" + allLocations.get(1).getLocationName() +
                "\nBitte die entsprechende Zahl eingeben";
        int choiceOfLocation = transformer.stringToInteger(userInterface.getUserInput(locationText), 2);
        vehicle.setStorageLocation(allLocations.get(choiceOfLocation - 1));
        return vehicle;
    }

    private Vehicle getVehicle() {
        String searchText = BOLD + "Welchen Artikel möchten Sie bearbeiten?\n" + RESET + "Bitte geben Sie die Artikelnummer ein";
        return finder.findByExternalId(searchText);
    }
}
