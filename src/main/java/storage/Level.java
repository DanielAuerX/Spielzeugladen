package storage;


import toy_features.Size;
import toys.Vehicle;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Level {
    private final int capacity = 10;
    private int remainingCapacity;
    private ArrayList<Vehicle> storedVehicles;

    public Level(ArrayList<Vehicle> storedVehicles) {
        this.storedVehicles = storedVehicles;
        remainingCapacity = getRemainingCapacity();
    }

    public ArrayList<Vehicle> getStoredVehicles() {
        return storedVehicles;
    }

   /* public ArrayList<Vehicle> getUnitsWithVehicles(ArrayList<Vehicle> storedVehicles){ //is needed?
        ArrayList<Vehicle> storageByUnits = new ArrayList<>();
        for (Vehicle storedVehicle : storedVehicles){
            Size size = storedVehicle.getSize();
            switch (size){
                case M -> storageByUnits.add(storedVehicle);
                case L -> {
                    storageByUnits.add(storedVehicle);
                    storageByUnits.add(storedVehicle);
                }
                case XL -> {
                    storageByUnits.add(storedVehicle);
                    storageByUnits.add(storedVehicle);
                    storageByUnits.add(storedVehicle);
                }
            }
        }
        return storageByUnits;
    }

    */

    public int getRemainingCapacity() {
        remainingCapacity = capacity;
        for (Vehicle storedVehicle : storedVehicles) {
            Size size = storedVehicle.getSize();
            switch (size){
                case M -> remainingCapacity --;
                case L -> remainingCapacity -= 2;
                case XL -> remainingCapacity -= 3;
            }
        }
        return remainingCapacity;
    }

    public String getFreeUnits(Size size){
        int unitsNeeded;
        switch (size){
            case M -> unitsNeeded = 1;
            case L -> unitsNeeded = 2;
            case XL -> unitsNeeded = 3;
            default -> throw new InputMismatchException("Unknown size!");
        }
        if (remainingCapacity < unitsNeeded) {
            return null;
        } else {
            int unitIndex = capacity - remainingCapacity + 1;
            if (unitsNeeded == 1){
                return String.valueOf(unitIndex);
            }
            else {
                return unitIndex+"+"+(unitsNeeded-1);
            }
        }
    }


}
