package toys;

import administration.*;
import interfaces.LandVehicle;
import interfaces.WaterVehicle;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Hovercraft extends Vehicle implements WaterVehicle, LandVehicle {
    private final String genericName = "ein Luftkissenboot";

    public Hovercraft(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
    }

    @Override
    public void drive() {
        printMovement(genericName, LandVehicle.typeOfMovement, LandVehicle.element);
    }

    @Override
    public void swim() {
        printMovement(genericName, WaterVehicle.typeOfMovement, WaterVehicle.element);
    }
}
