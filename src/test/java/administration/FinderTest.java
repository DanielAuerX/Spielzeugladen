package administration;

import data.JsonIO;
import data.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.Submarine;
import toys.Vehicle;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    private final JsonIO jsonIO = new JsonIO();
    private final Repository repository = new Repository();
    private final Finder finder = new Finder();

    @BeforeEach
    void setUp() {
        Address testAddress = new Address("Hauptstraße", "1a", 29664, "Bremen");
        Producer testProducer = new Producer("TEST_PRODUCER", testAddress, "0516112345", "produktion@test.com");
        Submarine testVehicle = new Submarine(UUID.randomUUID(),
                9999999,
                "TEST_SUB_1000",
                Color.BLACK, Size.M,
                testProducer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(100, Calendar.DECEMBER, 10),
                StorageLocation.LOCATION1);

        jsonIO.writeVehicleToData(testVehicle, repository.getConfig().getInventoryPath());

    }

    @AfterEach
    void tearDown() {
        Vehicle vehicle = repository.getVehicleByExternalId(9999999);
        jsonIO.writeVehicleToData(vehicle, repository.getConfig().getInventoryPath());
    }

    @Test
    void findByExternalId_ShouldFindTestVehicle() {
        String userInput = "9999999";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        Vehicle vehicle = finder.findByExternalId("test_text");

        assertEquals("TEST_SUB_1000", vehicle.getName());
    }

    @Test
    void findByExternalId_ShouldPrintTestText() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String userInput = "9999999";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        finder.findByExternalId("test_text");

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals("test_text: ", actual);
    }

    @Test
    void findByExternalId_WrongIdShouldThrowException() {
        String userInput = "1010101010101";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        assertThrows(RuntimeException.class, () -> {
            finder.findByExternalId("test_text");
        });
    }

    @Test
    void findByName_ShouldFindTestVehicle() {
        String userInput = "TEST_SUB_1000";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        Vehicle vehicle = finder.findByName();

        assertEquals(9999999, vehicle.getExternalId());
    }

    @Test
    void findByName_WrongNameShouldThrowException() {
        String userInput = "abcdefghijklmnop";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        assertThrows(InputMismatchException.class, finder::findByName);
    }

    @Test
    void findByProducer_ShouldFindVehiclesWithTheSameProducer() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ArrayList<Vehicle> byProducer = finder.findByProducer();

        Producer firstProducer = byProducer.get(0).getProducer();
        boolean isTheSameProducer = true;
        for (Vehicle vehicle : byProducer){
            if (!vehicle.getProducer().getName().equals(firstProducer.getName())) {
                isTheSameProducer = false;
                break;
            }
        }
        assertTrue(isTheSameProducer);
    }

    @Test
    void findByClass_ShouldFindVehiclesWithTheSameClass() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ArrayList<Vehicle> byClass = finder.findByClass();

        Class<? extends Vehicle> foundClass = byClass.get(0).getClass();
        boolean isTheSameClass = true;
        for (Vehicle vehicle : byClass){
            if (!vehicle.getClass().equals(foundClass)) {
                isTheSameClass = false;
                break;
            }
        }
        assertTrue(isTheSameClass);
    }

    @Test
    void findByColor_ShouldFindVehiclesWithTheSameColor() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ArrayList<Vehicle> byColor = finder.findByColor();

        Color foundColor = byColor.get(0).getColor();
        boolean isTheSameColor = true;
        for (Vehicle vehicle : byColor){
            if (!vehicle.getColor().equals(foundColor)) {
                isTheSameColor = false;
                break;
            }
        }
        assertTrue(isTheSameColor);
    }

    @Test
    void findBySize_ShouldFindVehiclesWithTheSameSize() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ArrayList<Vehicle> bySize = finder.findBySize();

        Size size = bySize.get(0).getSize();
        boolean isTheSameSize = true;
        for (Vehicle vehicle : bySize){
            if (!vehicle.getSize().equals(size)) {
                isTheSameSize = false;
                break;
            }
        }
        assertTrue(isTheSameSize);
    }

    @Test
    void findBySystemOfDrive_ShouldFindVehiclesWithTheSameSystem() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ArrayList<Vehicle> bySystemOfDrive = finder.findBySystemOfDrive();

        SystemOfDrive systemOfDrive = bySystemOfDrive.get(0).getSystemOfDrive();
        boolean isTheSameSystem = true;
        for (Vehicle vehicle : bySystemOfDrive){
            if (!vehicle.getSystemOfDrive().equals(systemOfDrive)) {
                isTheSameSystem = false;
                break;
            }
        }
        assertTrue(isTheSameSystem);
    }

    @Test
    void findByLocation_ShouldFindVehiclesWithTheSameLocation() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ArrayList<Vehicle> byLocation = finder.findByLocation();

        StorageLocation storageLocation = byLocation.get(0).getStorageLocation();
        boolean isTheSameLocation = true;
        for (Vehicle vehicle : byLocation){
            if (!vehicle.getStorageLocation().equals(storageLocation)) {
                isTheSameLocation = false;
                break;
            }
        }
        assertTrue(isTheSameLocation);
    }
}