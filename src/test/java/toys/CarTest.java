package toys;

import administration.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Address address = new Address("Steinstraße", "1a", 29664, "Bremen");
    Producer producer = new Producer("Toys4Us", address, "0516112345", "produktion@T4US.com");
    Car car = new Car(UUID.randomUUID(),
            1,
            "Speedo",
            Color.BLACK, Size.M,
            producer,
            10.00,
            15.00,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(10, Calendar.NOVEMBER, 2022),
            StorageLocation.LOCATION1,
            4);

    @Test
    void testToString_ShouldContainNumberOfWheels() {
        String expected = "Car{externalId=1, name='Speedo', color=java.awt.Color[r=0,g=0,b=0], size=M, producer=Producer{name='Toys4Us', address=Address{street='Steinstraße', houseNumber='1a', zipCode=29664, city='Bremen'}, phoneNumber=87594213, email='produktion@T4US.com'}, purchasePrice=10.0, salesPrice=15.0, systemOfDrive=ELEKTROMOTOR, deliveryDate=Sun May 14 00:00:00 CEST 1916, storageLocation=LOCATION1, numberOfWheels=4}";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        System.out.println(car);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test
    void drive() {
        String expected = "Hallo ich bin ein Auto und ich fahre auf dem Untergrund!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        car.drive();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test
    void car_ShouldHaveWheels(){
        assertEquals(4, car.getNumberOfWheels());
    }
}