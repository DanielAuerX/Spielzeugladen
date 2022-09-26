package administration;

import interfaces.LandVehicle;
import interfaces.WheeledVehicle;
import toy_features.*;
import toys.*;

import java.awt.*;
import java.util.*;

public class ToyAdministration {
    Repository repository = new Repository();
    UserInterface userInterface = new UserInterface();
    Transformer transformer = new Transformer();
    JsonIO jsonIO = new JsonIO();
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\033[0m";

    public void add(){
        Vehicle vehicle = createVehicle();
        jsonIO.writeVehicleData(vehicle, false);
    }

    public void edit() {
        Vehicle editedVehicle = editVehicle();
        jsonIO.writeVehicleData(editedVehicle, false);
    }


    private Vehicle createVehicle() {
        System.out.println("Bitte alle geforderten Informationen eingeben.");
        int type = getTypeNumberFromUser();
        String name = checkName(userInterface.askForInput("Modellname"));
        Color color = transformer.stringToColor(userInterface.askForInput("Farbe"));
        Size size = transformer.stringToSize(userInterface.askForInput("Größe (M, L oder XL)"));
        Producer producer = getProducerFromUser();
        double purchasePrice = Double.parseDouble(userInterface.askForInput("Einkaufspreis").replace(",", "."));
        double salesPrice = Double.parseDouble(userInterface.askForInput("Verkaufspreis").replace(",", "."));
        SystemOfDrive systemOfDrive = getSystemOfDriveFromUser();
        Date deliveryDate = transformer.stringToDate(userInterface.askForInput("Lieferdatum (dd.mm.yyyy)"));
        int numberOfWheels = 0;
        if (type > 5 && type < 10) {
            numberOfWheels = Integer.parseInt(userInterface.askForInput("Anzahl der Räder"));
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
            String input = userInterface.askForInput(outputText);
            switch (input) {
                case "j" -> nameOfNewVehicle = userInterface.askForInput("Bitte den neuen Namen eingeben");
                case "n" -> nameDoesExist = false;
                default -> throw new InputMismatchException("Falsche Eingabe! Bitte 'j' oder 'n' eingeben.");
            }
        }
        return nameOfNewVehicle;
    }

    private int getTypeNumberFromUser() {
        ArrayList<String> types = new  ArrayList<>(Arrays.asList("Segelboot", "Motorboot", "U-Boot", "Luftkissenboot", "Bulldozer", "Motorrad", "Auto", "LKW", "Fahrrad", "Hubschrauber", "Jet", "Segelflugzeug"));
        String table = transformer.listToMenuTable(types);
        String choiceAsString = userInterface.askForInput("Art des Spielzeugs\n" + table);
        return transformer.stringToInteger(choiceAsString);
    }

    private Producer getProducerFromUser(){
        String outputText = "Hersteller:\n0\t=\tneuen Hersteller anlegen\n"+ transformer.listToMenuTable(repository.getProducerNames());
        String choiceAsString = userInterface.askForInput(outputText);
        int choiceOfProducer = transformer.stringToInteger(choiceAsString);
        if (choiceOfProducer == 0){
            Producer producer = createProducer();
            jsonIO.addProducer(producer);
            return producer;
        }
        return repository.getProducer((choiceOfProducer));
    }

    private SystemOfDrive getSystemOfDriveFromUser(){
        ArrayList<String> systems = new  ArrayList<>(Arrays.asList("Dieselmotor", "Benzinmotor", "Elektromotor", "Kerosinmotor", "Grüne Energie"));
        String choice = userInterface.askForInput("Antriebsart\n" + transformer.listToMenuTable(systems));
        return transformer.stringToSystemOfDrive(choice);
    }

    private Producer createProducer(){
        UserInterface userInterface = new UserInterface();
        String name = userInterface.askForInput("Name des Herstellers");
        String street = userInterface.askForInput("Straße (Adresse)");
        String houseNumber = userInterface.askForInput("Hausnummer (Adresse)");
        int zipCode = Integer.parseInt(userInterface.askForInput("Postleitzahl (Adresse)"));
        String city = userInterface.askForInput("Stadt (Adresse)");
        String telephoneNumber = userInterface.askForInput("Telefonnummer");
        String email = userInterface.askForInput("Email");

        Address address = new Address(street, houseNumber, zipCode, city);
        return new Producer(name, address, telephoneNumber, email);
    }

    public String find(){
        String header = "Wählen sie eine Kategorie nach der Sie suchen möchten.\n";
        ArrayList<String> menuOptions = new ArrayList<>(Arrays.asList("Artikelnummer", "Spielzeugtyp", "Modellname", "Farbe", "Größe", "Hersteller", "Antriebsart"));
        String input = userInterface.askForInput(header + transformer.listToMenuTable(menuOptions));
        int categoryChoice = transformer.stringToInteger(input, menuOptions.size());;
        return startSearch(categoryChoice);
    }

    private String startSearch(int categoryChoice){
        Vehicle vehicle;
        switch (categoryChoice){
            case 1 : //externalId
                vehicle = findByExternalId("Bitte Artikelnummer eingeben");
                return vehicle.print();
            case 2 : //toy type
                return transformer.listToResultTable(findByClass());
            case 3 : //name
                vehicle = findByName();
                return vehicle.print();
            case 4 : //color
                return transformer.listToResultTable(findByColor());
            case 5 : //size
                return transformer.listToResultTable(findBySize());
            case 6 : //producer
                return transformer.listToResultTable(findByProducer());
            case 7 : //systemOfDrive
                return transformer.listToResultTable(findBySystemOfDrive());
            default:
                throw new InputMismatchException("Falsche Eingabe!");
        }
    }

    private Vehicle findByExternalId(String searchText) {
        String externalIdAsString = userInterface.askForInput(searchText);
        int externalId = transformer.stringToInteger(externalIdAsString);
        return repository.getVehicleByExternalId(externalId);
    }

    private Vehicle findByName(){
        String name = userInterface.askForInput("Bitte Modellname eingeben");
        return repository.getVehicleByName(name);
    }

    private ArrayList<Vehicle> findByProducer(){
        String outputText = "Wählen Sie einen Hersteller aus.\n" + transformer.listToMenuTable(repository.getProducerNames());
        int choiceAsInt = transformer.stringToInteger(userInterface.askForInput(outputText));
        Producer producer = repository.getProducer(choiceAsInt);
        return repository.getVehiclesByProducer(producer);
    }

    private ArrayList<Vehicle> findByClass(){
        ArrayList<String> classes = new  ArrayList<>(Arrays.asList("Segelboot", "Motorboot", "U-Boot", "Luftkissenboot", "Bulldozer", "Motorrad", "Auto", "LKW", "Fahrrad", "Hubschrauber", "Jet", "Segelflugzeug"));
        String table = "Wählen Sie einen Spielzeugtyp aus.\n" + transformer.listToMenuTable(classes);
        int choice = transformer.stringToInteger(userInterface.askForInput(table), classes.size());
        return repository.getVehiclesByClass(transformer.integerToClass(choice));
    }

    private ArrayList<Vehicle> findByColor(){
        ArrayList<String> colors = new  ArrayList<>(Arrays.asList("rot", "grün", "blau", "schwarz", "weiß", "gelb", "grau"));
        String table = "Wählen Sie eine Farbe aus.\n" + transformer.listToMenuTable(colors);
        int choice = transformer.stringToInteger(userInterface.askForInput(table), colors.size());
        Color color = transformer.stringToColor(colors.get(choice - 1));
        return repository.getVehiclesByColor(color);
    }

    private ArrayList<Vehicle> findBySize(){
        ArrayList<String> sizes = new  ArrayList<>(Arrays.asList("M", "L", "XL"));
        String table = "Wählen Sie eine Größe aus.\n" + transformer.listToMenuTable(sizes);
        int choice = transformer.stringToInteger(userInterface.askForInput(table), sizes.size());
        Size size = transformer.stringToSize(sizes.get(choice - 1));
        return repository.getVehiclesBySize(size);
    }

    private ArrayList<Vehicle> findBySystemOfDrive(){
        ArrayList<String> sizes = new  ArrayList<>(Arrays.asList("Dieselmotor", "Benzinmotor", "Elektromotor", "Kerosinmotor", "Grüne Energie"));
        String table = "Wählen Sie eine Antriebsart aus.\n" + transformer.listToMenuTable(sizes);
        int choice = transformer.stringToInteger(userInterface.askForInput(table), sizes.size());
        SystemOfDrive systemOfDrive = transformer.stringToSystemOfDrive(String.valueOf(choice));
        return repository.getVehiclesBySystemOfDrive(systemOfDrive);
    }

    public void delete(){
        String searchText = """
                Welchen Artikel möchten Sie löschen?
                Bitte geben Sie die Artikelnummer ein""";
        Vehicle vehicle = findByExternalId(searchText);
        jsonIO.writeVehicleData(vehicle, true);
    }


    private void editFeature(Vehicle vehicle, int featureNumber) {
        switch (featureNumber) {
            case 1 -> {
                String newName = userInterface.askForInput("Modellname");
                vehicle.setName(newName);
            }
            case 2 -> {
                String newColor = userInterface.askForInput("Farbe");
                vehicle.setColor(transformer.stringToColor(newColor));
            }
            case 3 -> {
                String newSize = userInterface.askForInput("Größe (M, L oder XL)");
                vehicle.setSize(transformer.stringToSize(newSize));
            }
            case 4 -> {
                Producer newProducer = getProducerFromUser();
                vehicle.setProducer(newProducer);
            }
            case 5 -> {
                String newPrice = userInterface.askForInput("Einkaufspreis)");
                vehicle.setPurchasePrice(Double.parseDouble(newPrice));
            }
            case 6 -> {
                String newPrice = userInterface.askForInput("Verkaufspreis)");
                vehicle.setSalesPrice(Double.parseDouble(newPrice));
            }
            case 7 -> vehicle.setSystemOfDrive(getSystemOfDriveFromUser());
            case 8 -> {
                Date deliveryDate = transformer.stringToDate(userInterface.askForInput("Lieferdatum (dd.mm.yyyy)"));
                vehicle.setDeliveryDate(deliveryDate);
            }
            case 9 -> {
                if (vehicle instanceof WheeledVehicle) {
                    int numberOfWheels = Integer.parseInt(userInterface.askForInput("Anzahl der Räder"));
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
        Vehicle vehicle = findByExternalId(searchText);
        ArrayList<String> options = new ArrayList<>(Arrays.asList("Modellname", "Farbe", "Größe", "Hersteller", "Einkaufspreis", "Verkaufspreis", "Antriebsart", "Lieferdatum"));
        if (vehicle instanceof WheeledVehicle) {
            options.add("Anzahl der Räder");
        }
        String header = vehicle.print() + BOLD + "\nWas möchten Sie bearbeiten?\n" + RESET;
        String featureNumberAsString = userInterface.askForInput(header + transformer.listToMenuTable(options));
        int featureNumber = transformer.stringToInteger(featureNumberAsString);
        editFeature(vehicle, featureNumber);
        return vehicle;
    }




}
