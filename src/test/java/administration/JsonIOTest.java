package administration;

import data.JsonIO;
import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.Sailboat;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JsonIOTest {

    JsonIO jsonIO = new JsonIO();
    Address address = new Address("abc", "1", 1, "abc");
    Producer testProducer = new Producer("Test-Hersteller_Test001", address, "1", "produktion@test.com");
    Sailboat testVehicle = new Sailboat(UUID.randomUUID(),
            1,
            "Test-Spielzeug_Test001",
            Color.BLACK, Size.M,
            testProducer,
            1,
            1,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(2022-1900, Calendar.JANUARY, 1),
            StorageLocation.LOCATION1);

    @Test
    void readJson_ShouldReturnStringWithContent() {
        String filepath = "R:\\Java\\Spielzeugladen\\test_data.json";
        writeDataToFile(filepath, "hallo");

        String dataFromMethod = jsonIO.readJson(filepath);

        assertFalse(dataFromMethod.isBlank());

        File testFile = new File(filepath);
        testFile.delete();
    }


    @Test
    void readJSON_ShouldReturnStringContainingInternalId() {
        String testString = "This is a test string";
        String filepath = "R:\\Java\\Spielzeugladen\\test_data.json";
        writeDataToFile(filepath, testString);

        String dataFromMethod = jsonIO.readJson(filepath);

        assertTrue(dataFromMethod.contains("This is a test string"));

        File testFile = new File(filepath);
        testFile.delete();
    }

    @Test
    void addProducer_ShouldAddTestProducerToFile() {
        String filepath = "R:\\Java\\Spielzeugladen\\producer_data.json";
        String producerDataBeforeTest = jsonIO.readJson(filepath);

        jsonIO.addProducer(testProducer);

        String producerDataAfterTest = jsonIO.readJson(filepath);
        assertTrue(producerDataAfterTest.contains("Test-Hersteller_Test001") && producerDataAfterTest.contains("produktion@test.com"));

        writeDataToFile(filepath, producerDataBeforeTest);
    }

    @Test
    void writeVehicleData_ShouldAddTestVehicleToFile() {
        jsonIO.writeVehicleData(testVehicle, false);

        String dataAfterTest = jsonIO.readJson("R:\\Java\\Spielzeugladen\\inventory_data.json");
        assertTrue(dataAfterTest.contains("Test-Spielzeug_Test001") && dataAfterTest.contains("Test-Hersteller_Test001"));

        jsonIO.writeVehicleData(testVehicle, true);
    }

    @Test
    void writeVehicleData_ShouldDeleteTestVehicle() {
        jsonIO.writeVehicleData(testVehicle, false);

        jsonIO.writeVehicleData(testVehicle, true);

        String dataAfterTest = jsonIO.readJson("R:\\Java\\Spielzeugladen\\inventory_data.json");
        assertFalse(dataAfterTest.contains("Test-Spielzeug_Test001") && dataAfterTest.contains("Test-Hersteller_Test001"));
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