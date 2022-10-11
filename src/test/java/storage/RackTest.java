package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.*;

import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RackTest {

    ArrayList<Level> listOfLevels  = new ArrayList<>();
    ArrayList<Vehicle> fullListOfVehicles = new ArrayList<>();
    ArrayList<Vehicle> listWithSomeVehicles = new ArrayList<>();
    ArrayList<Vehicle> emptyListOfVehicles = new ArrayList<>();

    @BeforeEach
    void setUp() {
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
        fullListOfVehicles.add(car);
        fullListOfVehicles.add(car);
        fullListOfVehicles.add(car);
        fullListOfVehicles.add(sailboat);
        listWithSomeVehicles.add(car);
        listWithSomeVehicles.add(car);
        Level fullLevel = new Level(fullListOfVehicles);
        Level levelWithEmptyUnits = new Level(listWithSomeVehicles);
        Level emptyLevel = new Level(emptyListOfVehicles);
        listOfLevels.add(fullLevel);
        listOfLevels.add(levelWithEmptyUnits);
        listOfLevels.add(emptyLevel);
    }

    @Test
    void rack_ConstructorShouldThrowExceptionIfTheListContainsMoreThanThreeLevels(){
        Level emptyLevel = new Level(emptyListOfVehicles);
        listOfLevels.add(emptyLevel);

        assertThrows(InputMismatchException.class, () -> {
            new Rack(listOfLevels);
        });
    }

    @Test
    void getLevelWithFreeUnits_RackWithFreeUnitsInTheSecondLevelShouldReturnB() {
        Rack testRack = new Rack(listOfLevels);

        String levelWithFreeUnits = testRack.getLevelWithFreeUnits(Size.M);

        assertTrue(levelWithFreeUnits.contains("b"));
    }

    @Test
    void getLevelWithFreeUnits_RackWithFreeUnitsInTheSecondLevelUnitSevenShouldReturnB7() {
        String expectedLevelWithFreeUnits = "b7";
        Rack testRack = new Rack(listOfLevels);

        String levelWithFreeUnits = testRack.getLevelWithFreeUnits(Size.M);

        assertEquals(expectedLevelWithFreeUnits, levelWithFreeUnits);
    }

    @Test
    void getLevelWithFreeUnits_RackWithFreeUnitsInTheThirdLevelUnitSevenShouldReturnC7() {
        String expectedLevelWithFreeUnits = "c7";
        listOfLevels.remove(1);
        listOfLevels.remove(1);
        Level fullLevel = new Level(fullListOfVehicles);
        Level levelWithEmptyUnits = new Level(listWithSomeVehicles);
        listOfLevels.add(fullLevel);
        listOfLevels.add(levelWithEmptyUnits);
        Rack testRack = new Rack(listOfLevels);

        String levelWithFreeUnits = testRack.getLevelWithFreeUnits(Size.M);

        assertEquals(expectedLevelWithFreeUnits, levelWithFreeUnits);
    }

    @Test
    void getLevelWithFreeUnits_RackWithNoFreeUnitsShouldReturnNull() {
        listOfLevels.remove(1);
        listOfLevels.remove(1);
        Level fullLevel = new Level(fullListOfVehicles);
        listOfLevels.add(fullLevel);
        listOfLevels.add(fullLevel);
        Rack testRack = new Rack(listOfLevels);

        String levelWithFreeUnits = testRack.getLevelWithFreeUnits(Size.M);

        assertNull(levelWithFreeUnits);
    }

}