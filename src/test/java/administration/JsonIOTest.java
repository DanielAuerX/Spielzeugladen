package administration;

import data.JsonIO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    private final String testFilepath = "test_data.json";
    private final JsonIO jsonIO = new JsonIO();
    private final Address address = new Address("abc", "1", 1, "abc");
    private final Producer testProducer = new Producer("Test-Hersteller_001", address, "1", "produktion@test.com");
    private final Sailboat testVehicle = new Sailboat(UUID.randomUUID(),
            1,
            "Test-Spielzeug_001",
            Color.BLACK, Size.M,
            testProducer,
            1,
            1,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(2022-1900, Calendar.JANUARY, 1),
            StorageLocation.LOCATION1);

    @BeforeEach
    void setUp() throws IOException {
        File file = new File(testFilepath);
        file.createNewFile();
        //writeDataToFile(testFilepath, "");
    }

    @AfterEach
    void tearDown() {
        new File(testFilepath).deleteOnExit();
    }


    @Test
    void readJson_ShouldReturnStringWithContent() {
        writeDataToFile(testFilepath, "hallo");

        String dataFromMethod = jsonIO.readJson(testFilepath);

        assertFalse(dataFromMethod.isBlank());
    }

    @Test
    void readJSON_ShouldReturnStringContainingTestString() {
        String testString = "This is a test string";
        writeDataToFile(testFilepath, testString);

        String dataFromMethod = jsonIO.readJson(testFilepath);

        assertEquals("This is a test string", dataFromMethod);
    }

    @Test
    void readJSON_ShouldBeAbleToReadEmptyFile() {
        String expected = "";

        String actual = jsonIO.readJson(testFilepath);

        assertEquals(expected, actual);
    }

    @Test
    void addProducer_ShouldAddTestProducerToFile() {
        String expected = "[{\"name\":\"Test-Hersteller_001\",\"address\":{\"street\":\"abc\",\"houseNumber\":\"1\",\"zipCode\":1,\"city\":\"abc\"},\"phoneNumber\":\"1\",\"email\":\"produktion@test.com\"}]";

        jsonIO.addProducer(testProducer, testFilepath);

        String actual = jsonIO.readJson(testFilepath);
        assertEquals(expected, actual);
    }

    @Test
    void addProducer_ShouldAddTestProducerToAFileThatContainsAProducer() {
        String dataForTest = "[{\"name\":\"Test-Hersteller_002\",\"address\":{\"street\":\"abc\",\"houseNumber\":\"1\",\"zipCode\":1,\"city\":\"abc\"},\"phoneNumber\":\"1\",\"email\":\"produktion@test.com\"}]";
        writeDataToFile(testFilepath, dataForTest);

        jsonIO.addProducer(testProducer, testFilepath);

        String actual = jsonIO.readJson(testFilepath);
        assertTrue(actual.contains("Test-Hersteller_002") && actual.contains("Test-Hersteller_001"));
    }


    @Test
    void writeVehicleToData_ShouldAddTestVehicleToEmptyFile() {
        String vehicleName = "Test-Spielzeug_001";
        String producerName = "Test-Hersteller_001";

        jsonIO.writeVehicleToData(testVehicle, testFilepath);

        String dataAfterTest = jsonIO.readJson(testFilepath);
        assertTrue(dataAfterTest.contains(vehicleName) && dataAfterTest.contains(producerName));
    }

    @Test
    void writeVehicleData_ShouldAddTestVehicleToFileThatContainsVehicle() {
        String dataForTest = "[{\"genericName\":\"Segelboot\",\"internalId\":\"6e68523f-7463-4c2c-8a61-63e6854b4d55\",\"externalId\":1,\"name\":\"Test-Spielzeug_002\",\"color\":{\"r\":0,\"g\":0,\"b\":0},\"size\":\"M\",\"producer\":{\"name\":\"Test-Hersteller_001\",\"address\":{\"street\":\"abc\",\"houseNumber\":\"1\",\"zipCode\":1,\"city\":\"abc\"},\"phoneNumber\":\"1\",\"email\":\"produktion@test.com\"},\"purchasePrice\":1.0,\"salesPrice\":1.0,\"systemOfDrive\":\"ELEKTROMOTOR\",\"deliveryDate\":\"Jan 1, 2022, 12:00:00 AM\",\"storageLocation\":\"LOCATION1\"}]\n";
        writeDataToFile(testFilepath, dataForTest);

        jsonIO.writeVehicleToData(testVehicle, testFilepath);

        String dataAfterTest = jsonIO.readJson(testFilepath);
        assertTrue(dataAfterTest.contains("Test-Spielzeug_001") && dataAfterTest.contains("Test-Spielzeug_002"));
    }

    @Test
    void writeVehicleData_ShouldEditNameOfTestVehicle() {
        Address address = new Address("abc", "1", 1, "abc");
        Producer testProducer = new Producer("Test-Hersteller_001", address, "1", "produktion@test.com");
        Sailboat testVehicleEdited = new Sailboat(testVehicle.getInternalId(),
                1,
                "Test-Spielzeug_001_EDITED",
                Color.BLACK, Size.M,
                testProducer,
                1,
                1,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022-1900, Calendar.JANUARY, 1),
                StorageLocation.LOCATION1);
        jsonIO.writeVehicleToData(testVehicle, testFilepath);  // add original version

        jsonIO.writeVehicleToData(testVehicleEdited, testFilepath); // replace with edited version

        String dataAfterTest = jsonIO.readJson(testFilepath);
        assertTrue(dataAfterTest.contains("Test-Spielzeug_001_EDITED") && !dataAfterTest.contains("Test-Spielzeug_001\","));
    }

    @Test
    void deleteVehicleFromData_ShouldDeleteTestVehicleFromTheFile() {
        jsonIO.writeVehicleToData(testVehicle, testFilepath);

        jsonIO.deleteVehicleFromData(testVehicle, testFilepath);

        String dataAfterTest = jsonIO.readJson(testFilepath);
        assertEquals("[]", dataAfterTest);
    }

    @Test
    void deleteVehicleFromData_ShouldNotDoAnythingIfVehicleDoesNotExistInData() {
        Address address = new Address("abc", "1", 1, "abc");
        Producer testProducer = new Producer("Test-Hersteller_001", address, "1", "produktion@test.com");
        Sailboat testVehicle2 = new Sailboat(UUID.randomUUID(),
                3,
                "Test-Spielzeug_003",
                Color.BLACK, Size.M,
                testProducer,
                1,
                1,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022-1900, Calendar.JANUARY, 1),
                StorageLocation.LOCATION1);
        jsonIO.writeVehicleToData(testVehicle, testFilepath);
        String dataBeforeTest = jsonIO.readJson(testFilepath);

        jsonIO.deleteVehicleFromData(testVehicle2, testFilepath);

        String dataAfterTest = jsonIO.readJson(testFilepath);
        assertEquals(dataBeforeTest, dataAfterTest);
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