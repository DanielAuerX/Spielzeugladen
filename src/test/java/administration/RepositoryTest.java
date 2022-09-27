package administration;

import data.Repository;
import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.Submarine;
import toys.Vehicle;

import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    Repository repository = new Repository();
    Address testAddress = new Address("Hauptstraße", "1a", 29664, "Bremen");
    Producer testProducer = new Producer("IKEA", testAddress, "0516112345", "produktion@ikea.com");
    Submarine testVehicle = new Submarine(UUID.randomUUID(),
            1,
            "U-Boot",
            Color.BLACK, Size.M,
            testProducer,
            10.00,
            15.00,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(100, 10, 10),
            StorageLocation.LOCATION1);


    @Test
    void getProducer_1ShouldReturnProducer() {

        Producer producer = repository.getProducer(1);

        assertEquals(testProducer.getClass(), producer.getClass());
    }

    @Test
    void getProducer_9999OutOfBoundIndexShouldThrowException() {
        int outOfBoundIndex = 9999;

        assertThrows(InputMismatchException.class, () -> {
            repository.getProducer(outOfBoundIndex);
        });
    }

    @Test
    void getProducer_0OutOfBoundIndexShouldThrowException() {
        int outOfBoundIndex = 0;

        assertThrows(InputMismatchException.class, () -> {
            repository.getProducer(outOfBoundIndex);
        });
    }

    @Test
    void getProducerNames_ShouldReturnListWithNames() {

        ArrayList<String> listOfNames = repository.getProducerNames();

        assertFalse(listOfNames.isEmpty());
    }

    @Test
    void getVehicleByExternalId_1001ShouldReturnVehicleGlider100() {
        String expectedName = "glider100";

        Vehicle actualVehicle = repository.getVehicleByExternalId(1001);

        assertEquals(expectedName, actualVehicle.getName());
    }

    @Test
    void getVehicleByExternalId_WrongIdShouldThrowException() {
        int wrongId = 1234567;

        assertThrows(InputMismatchException.class, () -> repository.getVehicleByExternalId(wrongId));
    }

    @Test
    void getVehicleByName_BmxPowerShouldReturnVehicleWithId1002() {
        String name = "BMX-POWER";
        int expectedExternalId = 1002;

        Vehicle actualVehicle = repository.getVehicleByName(name);

        assertEquals(expectedExternalId, actualVehicle.getExternalId());
    }

    @Test
    void getVehicleByName_LowerCaseNameShouldReturnVehicleWithId1002() {
        String name = "bmx-power";
        int expectedExternalId = 1002;

        Vehicle actualVehicle = repository.getVehicleByName(name);

        assertEquals(expectedExternalId, actualVehicle.getExternalId());
    }

    @Test
    void getVehicleByName_WrongNameShouldThrowException() {
        String name = "wrong name!?";

        assertThrows(InputMismatchException.class, () -> {
            repository.getVehicleByName(name);
        });
    }

    @Test
    void getVehicleByProducer_AllReturnedVehiclesShouldHaveTheSameProducer() {
        Producer producer = repository.getProducer(1);
        boolean isDifferentProducer = false;

        ArrayList<Vehicle> vehiclesByProducer = repository.getVehiclesByProducer(producer);

        for (Vehicle vehicle : vehiclesByProducer) {
            if (!vehicle.getProducer().getName().equals(producer.getName())) {
                isDifferentProducer = true;
                break;
            }
        }
        assertFalse(isDifferentProducer);
    }

    @Test
    void getVehicleByProducer_NonExistingProducerShouldReturnEmptyList() {
        Producer nonExistingProducer = testProducer;

        ArrayList<Vehicle> vehiclesByProducer = repository.getVehiclesByProducer(nonExistingProducer);

        assertTrue(vehiclesByProducer.isEmpty());
    }

    @Test
    void getVehicleByClass_AllReturnedVehiclesShouldHaveTheSameClass() {
        Submarine submarine = testVehicle;
        boolean isDifferentClass = false;

        ArrayList<Vehicle> vehiclesByClass = repository.getVehiclesByClass(submarine.getClass());

        for (Vehicle vehicle : vehiclesByClass) {
            if (!vehicle.getClass().equals(submarine.getClass())) {
                isDifferentClass = true;
                break;
            }
        }
        assertFalse(isDifferentClass);
    }

    @Test
    void getVehicleByClass_NonExistingClassInDataShouldReturnEmptyList() {
        Class<? extends String> testClass = "Test String".getClass();

        ArrayList<Vehicle> vehiclesByClass = repository.getVehiclesByClass(testClass);

        assertTrue(vehiclesByClass.isEmpty());
    }

    @Test
    void getVehicleByColor_AllReturnedVehiclesShouldHaveTheSameColor() {
        Color red = Color.red;
        boolean isDifferentColor = false;

        ArrayList<Vehicle> vehiclesByColor = repository.getVehiclesByColor(red);

        for (Vehicle vehicle : vehiclesByColor) {
            if (!vehicle.getColor().equals(red)) {
                isDifferentColor = true;
                break;
            }
        }
        assertFalse(isDifferentColor);
    }

    @Test
    void getVehicleByColor_NonExistingColorInDataShouldReturnEmptyList() {
        Color nonExistingColor = new Color(1, 2, 3);

        ArrayList<Vehicle> vehiclesByColor = repository.getVehiclesByColor(nonExistingColor);

        assertTrue(vehiclesByColor.isEmpty());
    }

    @Test
    void getVehicleBySize_AllReturnedVehiclesShouldHaveTheSameSize() {
        Size size = Size.M;
        boolean isDifferentSize = false;

        ArrayList<Vehicle> vehiclesBySize = repository.getVehiclesBySize(size);

        for (Vehicle vehicle : vehiclesBySize) {
            if (!vehicle.getSize().equals(size)) {
                isDifferentSize = true;
                break;
            }
        }
        assertFalse(isDifferentSize);
    }

    @Test
    void getVehicleBySystemOfDrive_AllReturnedVehiclesShouldHaveTheSameSystemOfDrive() {
        SystemOfDrive system = SystemOfDrive.ELEKTROMOTOR;
        boolean isDifferentSystemOfDrive = false;

        ArrayList<Vehicle> vehiclesBySystemOfDrive = repository.getVehiclesBySystemOfDrive(system);

        for (Vehicle vehicle : vehiclesBySystemOfDrive) {
            if (!vehicle.getSystemOfDrive().equals(system)) {
                isDifferentSystemOfDrive = true;
                break;
            }
        }
        assertFalse(isDifferentSystemOfDrive);
    }

    @Test
    void getHighestExternalId_ReturnedNumberShouldBeHigherOrEqualThanRandomIdFromData() {
        ArrayList<Vehicle> vehiclesBySize = repository.getVehiclesBySize(Size.M);
        Random random = new Random();
        Vehicle randomVehicle = vehiclesBySize.get(random.nextInt(vehiclesBySize.size()));

        int highestExternalId = repository.getHighestExternalId();

        assertTrue(highestExternalId >= randomVehicle.getExternalId());
    }






    /*
        @Test
    void getProducerNames() {
        String expected = "Hersteller:\n0\t=\tneuen Hersteller anlegen\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        repository.getProducerNames();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertTrue(actual.contains(expected));
    }
     */
}