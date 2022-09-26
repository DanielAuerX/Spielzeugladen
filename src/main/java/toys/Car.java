package toys;

import administration.*;
import interfaces.LandVehicle;
import interfaces.WheeledVehicle;
import toy_features.Producer;
import toy_features.Size;
import toy_features.StorageLocation;
import toy_features.SystemOfDrive;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Car extends Vehicle implements LandVehicle, WheeledVehicle {

    private final String genericName = "Auto";
    private int numberOfWheels;

    public Car(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation, int numberOfWheels) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public String print() {
        Transformer transformer = new Transformer();
        String format = "%-20s";
        return "Das Spielzeug hat folgende Merkmale" +
                String.format(format, "\n Artikelnummer: ") + externalId +
                String.format(format, "\n Bezeichnung: ") + name  +
                String.format(format, "\n Farbe: ") + transformer.colorToString(color) +
                String.format(format, "\n Größe: ") +  size +
                String.format(format, "\n Hersteller: ") + producer +
                String.format(format, "\n Einkaufspreis: ") + String.format("%.2f", purchasePrice) +" EUR"+
                String.format(format, "\n Verkaufspreis: ") + String.format("%.2f", salesPrice) +" EUR"+
                String.format(format, "\n Antriebsart: ") + systemOfDrive +
                String.format(format, "\n Lieferdatum: ") + transformer.dateToString(deliveryDate) +
                String.format(format, "\n Lagerort: ") + storageLocation +
                String.format(format, "\n Anzahl der Räder: ")+numberOfWheels;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public void drive() {
        printMovement(genericName, LandVehicle.typeOfMovement, LandVehicle.element);
    }

}
