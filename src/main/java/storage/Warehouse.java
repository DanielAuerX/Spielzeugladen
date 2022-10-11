package storage;

import toy_features.Address;
import toy_features.Size;
import toys.Vehicle;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Warehouse {
    private String name;
    private Address address;
    private String phoneNumber;
    private ArrayList<Rack> racks;
    //private boolean isFull;


    public Warehouse(String name, Address address, String phoneNumber, ArrayList<Rack> racks) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        if (racks.size() > 5){
            throw new InputMismatchException("A rack can only have five racks!");
        }
        this.racks = racks;
    }

    public ArrayList<Rack> getRacks() {
        return racks;
    }

    /*public Vehicle getVehicleByIndex(String index) {  //needed?
        String[] coordinates = index.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        int rackIndex = Integer.parseInt(coordinates[0]) - 1;
        int levelIndex;
        int unitIndex = Integer.parseInt(coordinates[2]) - 1;;
        switch (coordinates[1]){
            case "a" -> levelIndex = 0;
            case "b" -> levelIndex = 1;
            case "c" -> levelIndex = 2;
            default -> throw new InputMismatchException("Wrong index format");
        }
        Level level = racks.get(rackIndex).getLevels().get(levelIndex);
        return level.getUnitsWithVehicles(level.getStoredVehicles()).get(unitIndex);
    }

     */

    public ArrayList<Vehicle> getAllVehicles(){
        ArrayList<Vehicle> allVehicles;
        for (Rack rack : racks) {
            // ...
        }
        return null;
    }

    public String getRackWithFreeUnits(Size size){
        String rackIndex;
        int counter = 0;
        for (Rack rack : racks) {
            counter++;
            rackIndex = rack.getLevelWithFreeUnits(size);
            if (rackIndex != null){
                return counter+rackIndex;
            }
            if (counter > 5){
                throw new RuntimeException("A warehouse can only have five racks.");
            }
        }
        return null;
    }
}
