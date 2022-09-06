package toys;

import administration.*;
import interfaces.SubseaVehicle;
import interfaces.WaterVehicle;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Submarine extends Vehicle implements WaterVehicle, SubseaVehicle {

    private final String genericName = "ein U-Boot";

    public Submarine(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
    }

    @Override
    public void dive() {
        printMovement(genericName, SubseaVehicle.typeOfMovement, SubseaVehicle.element);
    }

    @Override
    public void swim() {
        printMovement(genericName, WaterVehicle.typeOfMovement,WaterVehicle.element);
    }
}
