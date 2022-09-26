package toys;

import interfaces.AirVehicle;
import toy_features.Producer;
import toy_features.Size;
import toy_features.StorageLocation;
import toy_features.SystemOfDrive;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public class Glider extends Vehicle implements AirVehicle {

    private final String genericName = "Segelflugzeug";

    public Glider(UUID internalId, int externalId, String name, Color color, Size size, Producer producer, double purchasePrice, double salesPrice, SystemOfDrive systemOfDrive, Date deliveryDate, StorageLocation storageLocation) {
        super(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
    }

    @Override
    public void fly() {
        printMovement(genericName, AirVehicle.typeOfMovement, AirVehicle.element);
    }
}
