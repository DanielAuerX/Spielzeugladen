package toys;

import org.junit.jupiter.api.Test;
import toy_features.*;

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
    void print_ShouldContainNumberOfWheels() {
        String expected = "Das Spielzeug hat folgende Merkmale\n" +
                " Artikelnummer:    1\n" +
                " Bezeichnung:      Speedo\n" +
                " Farbe:            schwarz\n" +
                " Größe:            M\n" +
                " Hersteller:       Toys4Us (Steinstraße 1a, 29664 Bremen; Telefonnummer 0516112345; Email produktion@T4US.com)\n" +
                " Einkaufspreis:    10,00 EUR\n" +
                " Verkaufspreis:    15,00 EUR\n" +
                " Antriebsart:      ELEKTROMOTOR\n" +
                " Lieferdatum:      14.05.1916\n" +
                " Lagerort:         LOCATION1\n" +
                " Anzahl der Räder: 4";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        System.out.println(car.print());

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test
    void drive_ShouldPrintExpectedText() {
        String expected = "Hallo ich bin ein Auto und ich fahre auf dem Untergrund!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        car.drive();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }
}