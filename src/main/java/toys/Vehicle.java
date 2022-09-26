package toys;

import administration.*;
import toy_features.Producer;
import toy_features.Size;
import toy_features.StorageLocation;
import toy_features.SystemOfDrive;

import java.awt.*;
import java.util.Date;
import java.util.UUID;

public abstract class Vehicle {
    protected UUID internalId;
    protected int externalId;
    protected String name;
    protected Color color;
    protected Size size;
    protected Producer producer;
    protected double purchasePrice;
    protected double salesPrice;
    protected SystemOfDrive systemOfDrive;
    protected Date deliveryDate;
    protected StorageLocation storageLocation;

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

    public String print() {
        Transformer transformer = new Transformer();
        String format = "%-20s";
        return "Das Spielzeug hat folgende Merkmale" +  //in hashmap??
                String.format(format, "\n Artikelnummer: ") + externalId +
                String.format(format, "\n Bezeichnung: ") + name  +
                String.format(format, "\n Farbe: ") + transformer.colorToString(color) +
                String.format(format, "\n Größe: ") +  size +
                String.format(format, "\n Hersteller: ") + producer +
                String.format(format, "\n Einkaufspreis: ") + String.format("%.2f", purchasePrice) +" EUR"+
                String.format(format, "\n Verkaufspreis: ") + String.format("%.2f", salesPrice) +" EUR"+
                String.format(format, "\n Antriebsart: ") + systemOfDrive +
                String.format(format, "\n Lieferdatum: ") + transformer.dateToString(deliveryDate) +
                String.format(format, "\n Lagerort: ") + storageLocation;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public Producer getProducer() {
        return producer;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public SystemOfDrive getSystemOfDrive() {
        return systemOfDrive;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public int getExternalId() {
        return externalId;
    }

    public UUID getInternalId() {return internalId;}

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public void setSystemOfDrive(SystemOfDrive systemOfDrive) {
        this.systemOfDrive = systemOfDrive;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    protected void printMovement(String genericName, String typeOfMovement, String element){
        System.out.println("Hallo ich bin ein "+genericName+" und ich "+typeOfMovement+element+"!");
    }

    public void printMovementOfVehicle(Vehicle vehicle){
        if (vehicle instanceof Jet){
            Jet newJet = (Jet)vehicle;
            newJet.fly();
        }
        else if (vehicle instanceof Glider) {
            Glider glider = (Glider) vehicle;
            glider.fly();
        }
        else if (vehicle instanceof Helicopter) {
            Helicopter heli = (Helicopter) vehicle;
            heli.fly();
        }
        else if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            car.drive();
        }
        else if (vehicle instanceof Bicycle) {
            Bicycle bike = (Bicycle) vehicle;
            bike.drive();
        }
        else if (vehicle instanceof Motorcycle) {
            Motorcycle bike = (Motorcycle) vehicle;
            bike.drive();
        }
        else if (vehicle instanceof Bulldozer) {
            Bulldozer bulldozer = (Bulldozer) vehicle;
            bulldozer.drive();
        }
        else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            truck.drive();
        }
        else if (vehicle instanceof Hovercraft) {
            Hovercraft hovercraft = (Hovercraft) vehicle;
            hovercraft.drive();
            hovercraft.swim();
        }
        else if (vehicle instanceof Submarine) {
            Submarine submarine = (Submarine) vehicle;
            submarine.dive();
            submarine.swim();
        }

    }
}
