package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.Car;
import toys.Sailboat;
import toys.Vehicle;

import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    ArrayList<Rack> listOfRacks  = new ArrayList<>();
    Address address = new Address("strasse", "1a", 24159, "Hamburg");

    @BeforeEach
    void setUp() {
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
        Sailboat sailboat = new Sailboat(UUID.randomUUID(),
                1,
                "Segelboot-R2D2",
                Color.BLACK, Size.M,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022 - 1900, Calendar.NOVEMBER, 1),
                StorageLocation.LOCATION1);
        ArrayList<Vehicle> fullListOfVehicles = new ArrayList<>();
        ArrayList<Vehicle> listWithSomeVehicles = new ArrayList<>();
        ArrayList<Vehicle> emptyListOfVehicles = new ArrayList<>();
        fullListOfVehicles.add(car);
        fullListOfVehicles.add(car);
        fullListOfVehicles.add(car);
        fullListOfVehicles.add(sailboat);
        listWithSomeVehicles.add(car);
        listWithSomeVehicles.add(sailboat);
        Level fullLevel = new Level(fullListOfVehicles);
        Level levelWithEmptyUnits = new Level(listWithSomeVehicles);
        Level emptyLevel = new Level(emptyListOfVehicles);
        ArrayList<Level> listOfLevelsWithFreeUnits  = new ArrayList<>();
        ArrayList<Level> listOfFullLevels  = new ArrayList<>();
        listOfLevelsWithFreeUnits.add(levelWithEmptyUnits);
        listOfLevelsWithFreeUnits.add(emptyLevel);
        listOfLevelsWithFreeUnits.add(emptyLevel);
        listOfFullLevels.add(fullLevel);
        listOfFullLevels.add(fullLevel);
        listOfFullLevels.add(fullLevel);
        Rack fullRack = new Rack(listOfFullLevels);
        Rack rackWithFreeUnits = new Rack(listOfLevelsWithFreeUnits);
        listOfRacks.add(fullRack);
        listOfRacks.add(fullRack);
        listOfRacks.add(fullRack);
        listOfRacks.add(rackWithFreeUnits);

    }

    @Test
    void warehouse_ConstructorShouldThrowExceptionIfTheListContainsMoreThanFiveRacks(){
        Rack rack = listOfRacks.get(0);
        listOfRacks.add(rack);
        listOfRacks.add(rack);

        assertThrows(InputMismatchException.class, () -> {
            new Warehouse("Steindamm-Keller", address, "+4912346", listOfRacks);
        });
    }

    @Test
    void getRackWithFreeUnits_FourRacksShouldReturnAnInitialFour() {
        String expectedRackWithFreeUnits = "4a5";
        Warehouse warehouse = new Warehouse("Steindamm-Keller", address, "+4912346", listOfRacks);

        String rackWithFreeUnits = warehouse.getRackWithFreeUnits(Size.M);

        assertEquals(expectedRackWithFreeUnits, rackWithFreeUnits);
    }

    @Test
    void getRackWithFreeUnits_FiveFullRacksShouldReturnNull() {
        listOfRacks.remove(3);
        Rack fullRack = listOfRacks.get(0);
        listOfRacks.add(fullRack);
        listOfRacks.add(fullRack);
        Warehouse warehouse = new Warehouse("Steindamm-Keller", address, "+4912346", listOfRacks);

        String rackWithFreeUnits = warehouse.getRackWithFreeUnits(Size.M);

        assertNull(rackWithFreeUnits);
    }
}