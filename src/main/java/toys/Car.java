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
    public String toString() {
        return "Car{" +
                "externalId=" + super.externalId +
                ", name='" + name + '\'' +
                ", color=" + super.color +
                ", size=" + super.size +
                ", producer=" + super.producer +
                ", purchasePrice=" + super.purchasePrice +
                ", salesPrice=" + super.salesPrice +
                ", systemOfDrive=" + super.systemOfDrive +
                ", deliveryDate=" + super.deliveryDate +
                ", storageLocation=" + super.storageLocation +
                ", numberOfWheels=" + numberOfWheels +
                '}';
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public void drive() {
        printMovement(genericName, LandVehicle.typeOfMovement, LandVehicle.element);
    }
}
