package administration;

import toys.*;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class ToyAdministration {

    public Vehicle createVehicle(){
        UserInterface userInterface = new UserInterface();
        Transformer transformer = new Transformer();
        Repository repository = new Repository();

        System.out.println("Bitte alle geforderten Informationen eingeben.");
        String name = userInterface.askForParameter("Name");
        Color color = transformer.stringToColor(userInterface.askForParameter("Farbe"));
        Size size = transformer.stringToSize(userInterface.askForParameter("Größe (M, L oder XL)"));
        Producer producer = repository.getProducer((userInterface.askForParameter(repository.getProducerNames())));
        double purchasePrice = Double.parseDouble(userInterface.askForParameter("Einkaufspreis").replace(",", "."));
        double salesPrice = Double.parseDouble(userInterface.askForParameter("Verkaufspreis").replace(",", "."));
        SystemOfDrive systemOfDrive = transformer.stringToSystemOfDrive(userInterface.askForParameter("Antriebsart\n1\t=\tDieselmotor\n2\t=\tBenzinmotor\n3\t=\tElektromotor\n4\t=\tKerosinmotor\n5\t=\tGrüne Energie\nBitte den entsprechende Zahl angeben"));
        Date deliveryDate = transformer.stringToDate(userInterface.askForParameter("Lieferdatum (dd.mm.yyyy)"));

        int counter = 0; //write method (get highest id +1)
        int externalId = counter+1;
        UUID internalId = UUID.randomUUID();


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
