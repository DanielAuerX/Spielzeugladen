package administration;

import data.Repository;
import toy_features.StorageLocation;
import toys.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class StorageAdministration {
    // get toys by storage location
    // change storage location of a toy
    // set default storage location
    // quit program

    private final Transformer transformer = new Transformer();
    private final UserInterface userInterface = new UserInterface();
    private final Repository repository = new Repository();

    public String find (){
        ArrayList<Vehicle> vehicles = findByLocation();
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

    private ArrayList<Vehicle> findByLocation (){
        ArrayList<String> locations = new  ArrayList<>(Arrays.asList("Lagerhalle 1", "Lagerhalle 2", "Lagerhalle 3"));
        String table = "Wählen Sie einen Lagerort aus.\n" + transformer.listToMenuTable(locations);
        int choice = transformer.stringToInteger(userInterface.askForInput(table), locations.size());
        StorageLocation location = transformer.integerToStorageLocation(choice);
        return repository.getVehiclesByStorageLocation(location);
    }




}