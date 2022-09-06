package toys;

import administration.*;
import interfaces.WaterVehicle;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Sailboat extends Vehicle implements WaterVehicle {

    private final String genericName = "ein Segelboot";
    public Sailboat(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
    }

    @Override
    public void swim() {
        printMovement(genericName, WaterVehicle.typeOfMovement, WaterVehicle.element);
    }
}
