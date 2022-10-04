package administration;

import data.JsonIO;
import data.Repository;
import interfaces.WheeledVehicle;
import toy_features.*;
import toys.*;

import java.awt.*;
import java.util.*;

public class ToyAdministration {
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\033[0m";
    Repository repository = new Repository();
    UserInterface userInterface = new UserInterface();
    Transformer transformer = new Transformer();
    JsonIO jsonIO = new JsonIO();
    Finder finder = new Finder();


    public void add(){
        Vehicle vehicle = createVehicle();
        jsonIO.writeVehicleToData(vehicle, repository.getConfig().getInventoryPath());
    }

    public void edit() {
        Vehicle editedVehicle = editVehicle();
        jsonIO.writeVehicleToData(editedVehicle, repository.getConfig().getInventoryPath());
    }

    public String find(){
        String header = "Wählen sie eine Kategorie nach der Sie suchen möchten.\n";
        ArrayList<String> menuOptions = new ArrayList<>(Arrays.asList("Artikelnummer", "Spielzeugtyp", "Modellname", "Farbe", "Größe", "Hersteller", "Antriebsart"));
        String input = userInterface.getUserInput(header + transformer.listToMenuTable(menuOptions));
        int categoryChoice = transformer.stringToInteger(input, menuOptions.size());;
        return startSearch(categoryChoice);
    }

    public void delete(){
        String searchText = """
                Welchen Artikel möchten Sie löschen?
                Bitte geben Sie die Artikelnummer ein""";
        Vehicle vehicle = finder.findByExternalId(searchText);
        jsonIO.deleteVehicleFromData(vehicle, repository.getConfig().getInventoryPath());
    }

    private Vehicle createVehicle() {
        System.out.println("Bitte alle geforderten Informationen eingeben.");
        int type = getTypeNumberFromUser();
        String name = checkName(userInterface.getUserInput("Modellname"));
        Color color = transformer.stringToColor(userInterface.getUserInput("Farbe"));
        Size size = transformer.stringToSize(userInterface.getUserInput("Größe (M, L oder XL)"));
        Producer producer = getProducerFromUser();
        double purchasePrice = Double.parseDouble(userInterface.getUserInput("Einkaufspreis").replace(",", "."));
        double salesPrice = Double.parseDouble(userInterface.getUserInput("Verkaufspreis").replace(",", "."));
        SystemOfDrive systemOfDrive = getSystemOfDriveFromUser();
        Date deliveryDate = transformer.stringToDate(userInterface.getUserInput("Lieferdatum (dd.mm.yyyy)"));
        int numberOfWheels = 0;
        if (type > 5 && type < 10) {
            numberOfWheels = Integer.parseInt(userInterface.getUserInput("Anzahl der Räder"));
        }
        int externalId = repository.getHighestExternalId() + 1;
        UUID internalId = UUID.randomUUID();

        return switch (type) {
            case 1 ->
                    new Sailboat(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 2 ->
                    new Motorboat(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 3 ->
                    new Submarine(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 4 ->
                    new Hovercraft(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 5 ->
                    new Bulldozer(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 6 ->
                    new Motorcycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1, numberOfWheels);
            case 7 ->
                    new Car(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1, numberOfWheels);
            case 8 ->
                    new Truck(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1, numberOfWheels);
            case 9 ->
                    new Bicycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1, numberOfWheels);
            case 10 ->
                    new Helicopter(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 11 ->
                    new Jet(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case 12 ->
                    new Glider(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            default -> throw new InputMismatchException("Falsche Eingabe!");
        };
    }

    private String checkName(String nameOfNewVehicle) {
        boolean nameDoesExist = true;
        while (nameDoesExist){
            try {
                repository.getVehicleByName(nameOfNewVehicle);
            } catch (InputMismatchException ime) {
                return nameOfNewVehicle;
            }
            String outputText = """
                    Dieser Modellname existiert bereits!
                    Möchten Sie den Namen ändern (j = ja / n = nein)""";
            String input = userInterface.getUserInput(outputText);
            switch (input) {
                case "j" -> nameOfNewVehicle = userInterface.getUserInput("Bitte den neuen Namen eingeben");
                case "n" -> nameDoesExist = false;
                default -> throw new InputMismatchException("Falsche Eingabe! Bitte 'j' oder 'n' eingeben.");
            }
        }
        return nameOfNewVehicle;
    }

    private int getTypeNumberFromUser() {
        ArrayList<String> types = new  ArrayList<>(Arrays.asList("Segelboot", "Motorboot", "U-Boot", "Luftkissenboot", "Bulldozer", "Motorrad", "Auto", "LKW", "Fahrrad", "Hubschrauber", "Jet", "Segelflugzeug"));
        String table = transformer.listToMenuTable(types);
        String choiceAsString = userInterface.getUserInput("Art des Spielzeugs\n" + table);
        return transformer.stringToInteger(choiceAsString);
    }

    private Producer getProducerFromUser(){
        String outputText = "Hersteller:\n0\t=\tneuen Hersteller anlegen\n"+ transformer.listToMenuTable(repository.getProducerNames());
        String choiceAsString = userInterface.getUserInput(outputText);
        int choiceOfProducer = transformer.stringToInteger(choiceAsString);
        if (choiceOfProducer == 0){
            Producer producer = createProducer();
            jsonIO.addProducer(producer, repository.getConfig().getProducerPath());
            return producer;
        }
        return repository.getProducer((choiceOfProducer));
    }

    private SystemOfDrive getSystemOfDriveFromUser(){
        ArrayList<String> systems = new  ArrayList<>(Arrays.asList("Dieselmotor", "Benzinmotor", "Elektromotor", "Kerosinmotor", "Grüne Energie"));
        String choice = userInterface.getUserInput("Antriebsart\n" + transformer.listToMenuTable(systems));
        return transformer.stringToSystemOfDrive(choice);
    }

    private Producer createProducer(){
        UserInterface userInterface = new UserInterface();
        String name = userInterface.getUserInput("Name des Herstellers");
        String street = userInterface.getUserInput("Straße (Adresse)");
        String houseNumber = userInterface.getUserInput("Hausnummer (Adresse)");
        int zipCode = Integer.parseInt(userInterface.getUserInput("Postleitzahl (Adresse)"));
        String city = userInterface.getUserInput("Stadt (Adresse)");
        String telephoneNumber = userInterface.getUserInput("Telefonnummer");
        String email = userInterface.getUserInput("Email");

        Address address = new Address(street, houseNumber, zipCode, city);
        return new Producer(name, address, telephoneNumber, email);
    }

    private String startSearch(int categoryChoice){
        Vehicle vehicle;
        switch (categoryChoice){
            case 1 : //externalId
                vehicle = finder.findByExternalId("Bitte Artikelnummer eingeben");
                return vehicle.print();
            case 2 : //toy type
                return transformer.listToResultTable(finder.findByClass());
            case 3 : //name
                vehicle = finder.findByName();
                return vehicle.print();
            case 4 : //color
                return transformer.listToResultTable(finder.findByColor());
            case 5 : //size
                return transformer.listToResultTable(finder.findBySize());
            case 6 : //producer
                return transformer.listToResultTable(finder.findByProducer());
            case 7 : //systemOfDrive
                return transformer.listToResultTable(finder.findBySystemOfDrive());
            default:
                throw new InputMismatchException("Falsche Eingabe!");
        }
    }

    private void editFeature(Vehicle vehicle, int featureNumber) {
        switch (featureNumber) {
            case 1 -> {
                String newName = userInterface.getUserInput("Modellname");
                vehicle.setName(newName);
            }
            case 2 -> {
                String newColor = userInterface.getUserInput("Farbe");
                vehicle.setColor(transformer.stringToColor(newColor));
            }
            case 3 -> {
                String newSize = userInterface.getUserInput("Größe (M, L oder XL)");
                vehicle.setSize(transformer.stringToSize(newSize));
            }
            case 4 -> {
                Producer newProducer = getProducerFromUser();
                vehicle.setProducer(newProducer);
            }
            case 5 -> {
                String newPrice = userInterface.getUserInput("Einkaufspreis)");
                vehicle.setPurchasePrice(Double.parseDouble(newPrice));
            }
            case 6 -> {
                String newPrice = userInterface.getUserInput("Verkaufspreis)");
                vehicle.setSalesPrice(Double.parseDouble(newPrice));
            }
            case 7 -> vehicle.setSystemOfDrive(getSystemOfDriveFromUser());
            case 8 -> {
                Date deliveryDate = transformer.stringToDate(userInterface.getUserInput("Lieferdatum (dd.mm.yyyy)"));
                vehicle.setDeliveryDate(deliveryDate);
            }
            case 9 -> {
                if (vehicle instanceof WheeledVehicle) {
                    int numberOfWheels = Integer.parseInt(userInterface.getUserInput("Anzahl der Räder"));
                    ((WheeledVehicle) vehicle).setNumberOfWheels(numberOfWheels);
                } else {
                    throw new InputMismatchException("Falsche Eingabe!");
                }
            }
            default -> throw new InputMismatchException("Falsche Eingabe!");
        }
    }

    private Vehicle editVehicle() {
        String searchText = BOLD + "Welchen Artikel möchten Sie bearbeiten?\n" + RESET + "Bitte geben Sie die Artikelnummer ein";
        Vehicle vehicle = finder.findByExternalId(searchText);
        ArrayList<String> options = new ArrayList<>(Arrays.asList("Modellname", "Farbe", "Größe", "Hersteller", "Einkaufspreis", "Verkaufspreis", "Antriebsart", "Lieferdatum"));
        if (vehicle instanceof WheeledVehicle) {
            options.add("Anzahl der Räder");
        }
        String header = vehicle.print() + BOLD + "\nWas möchten Sie bearbeiten?\n" + RESET;
        String featureNumberAsString = userInterface.getUserInput(header + transformer.listToMenuTable(options));
        int featureNumber = transformer.stringToInteger(featureNumberAsString);
        editFeature(vehicle, featureNumber);
        return vehicle;
    }




}
