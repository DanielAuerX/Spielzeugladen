package administration;

import data.Repository;
import toy_features.Producer;
import toy_features.Size;
import toy_features.StorageLocation;
import toy_features.SystemOfDrive;
import toys.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Finder {
    private final UserInterface userInterface = new UserInterface();
    private final Transformer transformer = new Transformer();
    private final Repository repository = new Repository();

    public Vehicle findByExternalId(String searchText) {
        String externalIdAsString = userInterface.getUserInput(searchText);
        int externalId = transformer.stringToInteger(externalIdAsString);
        return repository.getVehicleByExternalId(externalId);
    }

    public Vehicle findByName(){
        String name = userInterface.getUserInput("Bitte Modellname eingeben");
        return repository.getVehicleByName(name);
    }

    public ArrayList<Vehicle> findByProducer(){
        String outputText = "Wählen Sie einen Hersteller aus.\n" + transformer.listToMenuTable(repository.getProducerNames());
        int choiceAsInt = transformer.stringToInteger(userInterface.getUserInput(outputText));
        Producer producer = repository.getProducer(choiceAsInt);
        return repository.getVehiclesByProducer(producer);
    }

    public ArrayList<Vehicle> findByClass(){
        ArrayList<String> classes = new  ArrayList<>(Arrays.asList("Segelboot", "Motorboot", "U-Boot", "Luftkissenboot", "Bulldozer", "Motorrad", "Auto", "LKW", "Fahrrad", "Hubschrauber", "Jet", "Segelflugzeug"));
        String table = "Wählen Sie einen Spielzeugtyp aus.\n" + transformer.listToMenuTable(classes);
        int choice = transformer.stringToInteger(userInterface.getUserInput(table), classes.size());
        return repository.getVehiclesByClass(transformer.integerToClass(choice));
    }

    public ArrayList<Vehicle> findByColor(){
        ArrayList<String> colors = new  ArrayList<>(Arrays.asList("rot", "grün", "blau", "schwarz", "weiß", "gelb", "grau"));
        String table = "Wählen Sie eine Farbe aus.\n" + transformer.listToMenuTable(colors);
        int choice = transformer.stringToInteger(userInterface.getUserInput(table), colors.size());
        Color color = transformer.stringToColor(colors.get(choice - 1));
        return repository.getVehiclesByColor(color);
    }

    public ArrayList<Vehicle> findBySize(){
        ArrayList<String> sizes = new  ArrayList<>(Arrays.asList("M", "L", "XL"));
        String table = "Wählen Sie eine Größe aus.\n" + transformer.listToMenuTable(sizes);
        int choice = transformer.stringToInteger(userInterface.getUserInput(table), sizes.size());
        Size size = transformer.stringToSize(sizes.get(choice - 1));
        return repository.getVehiclesBySize(size);
    }

    public ArrayList<Vehicle> findBySystemOfDrive(){
        ArrayList<String> sizes = new  ArrayList<>(Arrays.asList("Dieselmotor", "Benzinmotor", "Elektromotor", "Kerosinmotor", "Grüne Energie"));
        String table = "Wählen Sie eine Antriebsart aus.\n" + transformer.listToMenuTable(sizes);
        int choice = transformer.stringToInteger(userInterface.getUserInput(table), sizes.size());
        SystemOfDrive systemOfDrive = transformer.stringToSystemOfDrive(String.valueOf(choice));
        return repository.getVehiclesBySystemOfDrive(systemOfDrive);
    }

    public ArrayList<Vehicle> findByLocation() {
        ArrayList<String> locations = new ArrayList<>(Arrays.asList("Lagerhalle 1", "Lagerhalle 2", "Lagerhalle 3"));
        String table = "Wählen Sie einen Lagerort aus.\n" + transformer.listToMenuTable(locations);
        int choice = transformer.stringToInteger(userInterface.getUserInput(table), locations.size());
        StorageLocation location = transformer.integerToStorageLocation(choice);
        return repository.getVehiclesByStorageLocation(location);
    }





}
