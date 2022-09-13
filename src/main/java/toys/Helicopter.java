package toys;

import administration.*;
import interfaces.AirVehicle;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Helicopter extends Vehicle implements AirVehicle {

    private final String genericName = "ein Hubschrauber";

    public Helicopter(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
    }

    @Override
    public void fly() {
        printMovement(genericName, AirVehicle.typeOfMovement, AirVehicle.element);
    }
}
