package administration;

import data.Repository;
import toy_features.StorageLocation;
import toys.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static administration.ToyAdministration.BOLD;
import static administration.ToyAdministration.RESET;

public class StorageAdministration {
    // get toys by storage location
    // change storage location of a toy
    // set default storage location
    // quit program

    private final Transformer transformer = new Transformer();
    private final UserInterface userInterface = new UserInterface();
    private final Repository repository = new Repository();
    private final Finder finder = new Finder();

    public String find (){
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

    private void changeLocation () {
        String searchText = BOLD + "Welchen Artikel möchten Sie bearbeiten?\n" + RESET + "Bitte geben Sie die Artikelnummer ein";
        Vehicle vehicle = finder.findByExternalId(searchText);
    }




}
