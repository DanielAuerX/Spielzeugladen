package storage;

import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.*;

import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

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

    @Test
    void getRemainingCapacity_ShouldReturn1WithThreeXL() {
        int expectedRemainingCapacity = 1;
        toys.add(hovercraft);
        toys.add(car);
        toys.add(jet);
        Level testLevel = new Level(toys);

        int remainingCapacity = testLevel.getRemainingCapacity();

        assertEquals(expectedRemainingCapacity, remainingCapacity);
    }
    @Test
    void getRemainingCapacity_ShouldReturn0WithThreeXLAndOneM() {
        int expectedRemainingCapacity = 0;
        toys.add(hovercraft);
        toys.add(car);
        toys.add(jet);
        toys.add(sailboat);
        Level testLevel = new Level(toys);

        int remainingCapacity = testLevel.getRemainingCapacity();

        assertEquals(expectedRemainingCapacity, remainingCapacity);
    }

    @Test
    void getFreeUnits_SizeMVehicleOnALevelWithTwoXLShouldReturn7(){
        String expectedFreeUnits = "7";
        toys.add(car);
        toys.add(jet);
        Level testLevel = new Level(toys);

        String freeUnits = testLevel.getFreeUnits(Size.M);

        assertEquals(expectedFreeUnits, freeUnits);
    }

    @Test
    void getFreeUnits_SizeLVehicleOnALevelWithTwoXLShouldReturn7Plus1(){
        String expectedFreeUnits = "7+1";
        toys.add(car);
        toys.add(jet);
        Level testLevel = new Level(toys);

        String freeUnits = testLevel.getFreeUnits(Size.L);

        assertEquals(expectedFreeUnits, freeUnits);
    }

    @Test
    void getFreeUnits_SizeXLVehicleOnALevelWithTwoXLShouldReturn7Plus2(){
        String expectedFreeUnits = "7+2";
        toys.add(car);
        toys.add(jet);
        Level testLevel = new Level(toys);

        String freeUnits = testLevel.getFreeUnits(Size.XL);

        assertEquals(expectedFreeUnits, freeUnits);
    }

    @Test
    void getFreeUnits_SizeMVehicleOnAnEmptyLevelShouldReturn1(){
        String expectedFreeUnits = "1";
        Level testLevel = new Level(toys);

        String freeUnits = testLevel.getFreeUnits(Size.M);

        assertEquals(expectedFreeUnits, freeUnits);
    }

    @Test
    void getFreeUnits_SizeMVehicleOnAFullLevelShouldReturnNull(){
        toys.add(car);
        toys.add(jet);
        toys.add(hovercraft);
        toys.add(sailboat);
        Level testLevel = new Level(toys);

        String freeUnits = testLevel.getFreeUnits(Size.M);

        assertNull(freeUnits);
    }

    @Test
    void getFreeUnits_SizeLVehicleOnALevelWithThreeXLShouldReturnNull(){
        toys.add(car);
        toys.add(jet);
        toys.add(hovercraft);
        Level testLevel = new Level(toys);

        String freeUnits = testLevel.getFreeUnits(Size.L);

        assertNull(freeUnits);
    }



}