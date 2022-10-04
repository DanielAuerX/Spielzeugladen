package administration;

import data.JsonIO;
import data.Repository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.Sailboat;

import java.awt.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ToyAdministrationTest {

    JsonIO jsonIO = new JsonIO();
    ToyAdministration toyAdministration = new ToyAdministration();
    Repository repository = new Repository();
    Address address = new Address("abc", "1", 1, "abc");
    Producer testProducer = new Producer("Test-Hersteller_Test001", address, "1", "produktion@test.com");
    Sailboat testVehicle = new Sailboat(UUID.randomUUID(),
            123456789,
            "Test-Spielzeug_Test001",
            Color.BLACK, Size.M,
            testProducer,
            1,
            1,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(2022-1900, Calendar.JANUARY, 1),
            StorageLocation.LOCATION1);


    @Test
    void add() {
    }

    @Test
    void edit() {
    }

    @Test
    @Disabled("no second input")
    void find_ShouldReturnTestVehiclesData() {
        String filepath = "R:\\Java\\Spielzeugladen\\inventory_data.json";
        String dataBeforeTest = jsonIO.readJson(filepath);
        jsonIO.writeVehicleToData(testVehicle, filepath);
        String expected = "Das Spielzeug hat folgende Merkmale...";
        String userInput = "1\n" + "\n123456789";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String actual = toyAdministration.find();

        assertEquals(expected, actual);

        writeDataToFile(filepath, dataBeforeTest);
    }

    @Test
    @Disabled("TEST FILE")
    void delete_ShouldDeleteTestVehicle() {
        String filepath = "R:\\Java\\Spielzeugladen\\inventory_data.json";
        String dataBeforeTest = jsonIO.readJson(filepath);
        jsonIO.writeVehicleToData(testVehicle, filepath);
        String userInput = "123456789";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        toyAdministration.delete();

        assertThrows(InputMismatchException.class, () -> {repository.getVehicleByExternalId(123456789);});

        writeDataToFile(filepath, dataBeforeTest);
    }

    private static void writeDataToFile(String filepath, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(data);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}