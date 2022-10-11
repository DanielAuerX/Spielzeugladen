package administration;

import storage.Level;
import storage.Rack;
import storage.Warehouse;
import toy_features.*;
import toys.*;

import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("strasse", "1a", 24159, "Hamburg");
        Producer producer = new Producer("Toys4Us", address, "0516112345", "produktion@T4US.com");
        Car car = new Car(UUID.randomUUID(),
                1,
                "Speedo",
                Color.BLACK, Size.XL,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(10, Calendar.NOVEMBER, 2022),
                StorageLocation.LOCATION1,
                4);

        Hovercraft hovercraft = new Hovercraft(UUID.randomUUID(),
                1,
                "Puff-Puff-Kissenboot",
                Color.BLACK, Size.XL,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022-1900, Calendar.NOVEMBER, 1),
                StorageLocation.LOCATION1);

        Jet jet = new Jet(UUID.randomUUID(),
                1,
                "Wum-Wum-Jet",
                Color.BLACK, Size.XL,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022-1900, Calendar.NOVEMBER, 1),
                StorageLocation.LOCATION1);

        Sailboat sailboat = new Sailboat(UUID.randomUUID(),
                1,
                "Segelboot-R2D2",
                Color.BLACK, Size.M,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022-1900, Calendar.NOVEMBER, 1),
                StorageLocation.LOCATION1);

        ArrayList<Vehicle> toys = new ArrayList<>();
        ArrayList<Vehicle> toys2 = new ArrayList<>();
        toys.add(car);
        toys.add(hovercraft);
        toys.add(jet);
        toys.add(sailboat);

        toys2.add(car);
        toys2.add(hovercraft);

        Level levelA = new Level(toys);
        Level levelB = new Level(toys2);
        System.out.println(levelA.getStoredVehicles());
        System.out.println(levelA.getRemainingCapacity());
        ArrayList<Level> listOfLevels  = new ArrayList<>();
        ArrayList<Level> fullLevels  = new ArrayList<>();
        listOfLevels.add(levelA);
        listOfLevels.add(levelB);

        fullLevels.add(levelA);
        fullLevels.add(levelA);
        fullLevels.add(levelA);

        Rack rack = new Rack(listOfLevels);
        Rack fullRack = new Rack(fullLevels);
        System.out.println(rack.getLevels());
        ArrayList<Rack> listOfRacks  = new ArrayList<>();
        listOfRacks.add(fullRack);
        listOfRacks.add(rack);
        Warehouse warehouse = new Warehouse("Steindamm-Keller", address, "+4912346", listOfRacks);
        Level testLevel = warehouse.getRacks().get(0).getLevels().get(0);
        System.out.println(levelA.getRemainingCapacity());
        System.out.println(warehouse.getRackWithFreeUnits(Size.L));

        //Vehicle vehicleByIndex = warehouse.getVehicleByIndex("1a10+2");
        //System.out.println(vehicleByIndex.getName());

        //ArrayList<Vehicle> storageByUnits = testLevel.getUnitsWithVehicles(testLevel.getStoredVehicles());
        //System.out.println(storageByUnits);

        /*UserInterface userInterface = new UserInterface();
        userInterface.run();

         */
    }
}
