package administration;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public abstract class Vehicle {
    UUID internalId;
    int externalId;
    protected String name;
    Color color;
    Size size;
    Producer producer;
    double purchasePrice;
    double salesPrice;
    SystemOfDrive systemOfDrive;
    Date deliveryDate;
    StorageLocation storageLocation;

    public Vehicle(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        this.internalId = internalId;
        this.externalId = externalId;
        this.name = name;
        this.color = color;
        this.size = size;
        this.producer = producer;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;
        this.systemOfDrive = systemOfDrive;
        this.deliveryDate = deliveryDate;
        this.storageLocation = storageLocation;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "externalId=" + externalId +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", size=" + size +
                ", producer=" + producer +
                ", purchasePrice=" + purchasePrice +
                ", salesPrice=" + salesPrice +
                ", systemOfDrive=" + systemOfDrive +
                ", deliveryDate=" + deliveryDate +
                ", storageLocation=" + storageLocation +
                '}';
    }

    public String getName() {
        return name;
    }

    protected void printMovement(String genericName, String typeOfMovement, String element){
        System.out.println("Hallo ich bin "+genericName+" und ich "+typeOfMovement+element+"!");
    }
}
