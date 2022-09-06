package toys;

import administration.*;
import interfaces.LandVehicle;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Bicycle extends Vehicle implements LandVehicle {

    private final String genericName = "ein Fahrrad";

    public Bicycle(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
    }

    @Override
    public void drive() {
        printMovement(genericName, LandVehicle.typeOfMovement, LandVehicle.element);
    }
}
