package toys;

import administration.*;
import interfaces.LandVehicle;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Car extends Vehicle implements LandVehicle {

    private final String genericName = "ein Auto";
    private int numberOfWheels;

    public Car(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation, int numberOfWheels) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public String print() {
        Transformer transformer = new Transformer();
        return "Das Spielzeug hat folgende Merkmale:" +
                "\n Identifikationsnummer " + externalId +
                "\n Name " + name  +
                "\n Farbe " + transformer.colorToString(color) +
                "\n Größe " + size +
                "\n Hersteller " + producer +
                "\n Einkaufspreis " + String.format("%.2f", purchasePrice) +"EUR"+
                "\n Verkaufspreis " + String.format("%.2f", salesPrice) +"EUR"+
                "\n Antriebsart " + systemOfDrive +
                "\n Lieferdatum " + deliveryDate +
                "\n Lagerort " + storageLocation +
                "\n Anzahl der Räder "+numberOfWheels;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public void drive() {
        printMovement(genericName, LandVehicle.typeOfMovement, LandVehicle.element);
    }
}
