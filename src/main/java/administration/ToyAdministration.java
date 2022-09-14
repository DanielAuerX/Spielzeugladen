package administration;

import toys.*;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.UUID;

public class ToyAdministration {

    public Vehicle createVehicle() {
        UserInterface userInterface = new UserInterface();
        Transformer transformer = new Transformer();
        Repository repository = new Repository();

        System.out.println("Bitte alle geforderten Informationen eingeben.");
        int type = Integer.parseInt(userInterface.askForParameter(getVehicleTypes()));
        String name = userInterface.askForParameter("Name");
        Color color = transformer.stringToColor(userInterface.askForParameter("Farbe"));
        Size size = transformer.stringToSize(userInterface.askForParameter("Größe (M, L oder XL)"));
        Producer producer = repository.getProducer((userInterface.askForParameter(repository.getProducerNames())));
        double purchasePrice = Double.parseDouble(userInterface.askForParameter("Einkaufspreis").replace(",", "."));
        double salesPrice = Double.parseDouble(userInterface.askForParameter("Verkaufspreis").replace(",", "."));
        SystemOfDrive systemOfDrive = transformer.stringToSystemOfDrive(userInterface.askForParameter("Antriebsart\n1\t=\tDieselmotor\n2\t=\tBenzinmotor\n3\t=\tElektromotor\n4\t=\tKerosinmotor\n5\t=\tGrüne Energie\nBitte den entsprechende Zahl angeben"));
        Date deliveryDate = transformer.stringToDate(userInterface.askForParameter("Lieferdatum (dd.mm.yyyy)"));
        int numberOfWheels = 0;
        if (type > 5 && type < 10) {
            numberOfWheels = Integer.parseInt(userInterface.askForParameter("Anzahl der Räder"));
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

        /*Object instance;
        try {
            Constructor<?> constructor = Class.forName("toys." + "Jet").getConstructor(UUID.class, Integer.class, String.class, Color.class, Size.class, Producer.class, Double.class, Double.class, SystemOfDrive.class, Date.class, StorageLocation.class);
            instance = constructor.newInstance(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return instance;

         */
        /*
        name = name.toLowerCase();
        switch (name) {
            case "jet":
                return new Jet(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case "auto": {
                int numberOfWheels = Integer.parseInt(userInterface.askForParameter("Anzahl der Räder"));
                return new Car(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1, numberOfWheels);
            }
            case "u-boot":
            case "uboot":
                return new Submarine(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case "segelboot":
                return new Sailboat(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case "luftkissenboot":
                return new Hovercraft(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            case "motorboot":
                return new Motorboat(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1);
            default: {
                int numberOfWheels = Integer.parseInt(userInterface.askForParameter("Anzahl der Räder"));
                return new Bicycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, StorageLocation.LOCATION1, numberOfWheels);
            }
        }

         */
    }

    private String getVehicleTypes() {
        String[] types = new String[]{"Segelboot", "Motorboot", "U-Boot", "Luftkissenboot", "Bulldozer", "Motorrad", "Auto", "LKW", "Fahrrad", "Hubschrauber", "Jet", "Segelflugzeug"};
        int counter = 1;
        StringBuilder returnText = new StringBuilder("Art des Spielzeugs\n");
        for (String type : types) {
            returnText.append(counter).append("\t=\t").append(type).append("\n");
            counter++;
        }
        returnText.append("Bitte die entsprechende Zahl eingeben");
        return returnText.toString();
    }

    public Producer createProducer(){
        UserInterface userInterface = new UserInterface();
        String name = userInterface.askForParameter("Name des Herstellers");
        String street = userInterface.askForParameter("Straße (Adresse)");
        String houseNumber = userInterface.askForParameter("Hausnummer (Adresse)");
        int zipCode = Integer.parseInt(userInterface.askForParameter("Postleitzahl (Adresse)"));
        String city = userInterface.askForParameter("Stadt (Adresse)");
        String telephoneNumber = userInterface.askForParameter("Telefonnummer");
        String email = userInterface.askForParameter("Email");

        Address address = new Address(street, houseNumber, zipCode, city);
        Producer producer = new Producer(name, address, telephoneNumber, email);
        return producer;
    }
}
