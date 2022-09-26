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

class HovercraftTest {

    Address address = new Address("Steinstraße", "1a", 29664, "Bremen");
    Producer producer = new Producer("Toys4Us", address, "0516112345", "produktion@T4US.com");
    Hovercraft hovercraft = new Hovercraft(UUID.randomUUID(),
            1,
            "Puff-Puff-Kissenboot",
            Color.BLACK, Size.M,
            producer,
            10.00,
            15.00,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(2022-1900, Calendar.NOVEMBER, 1),
            StorageLocation.LOCATION1);

    @Test
    void print_ShouldPrintExpectedText() {
        String expected = "Das Spielzeug hat folgende Merkmale\n" +
                " Artikelnummer:    1\n" +
                " Bezeichnung:      Puff-Puff-Kissenboot\n" +
                " Farbe:            schwarz\n" +
                " Größe:            M\n" +
                " Hersteller:       Toys4Us (Steinstraße 1a, 29664 Bremen; Telefonnummer 0516112345; Email produktion@T4US.com)\n" +
                " Einkaufspreis:    10,00 EUR\n" +
                " Verkaufspreis:    15,00 EUR\n" +
                " Antriebsart:      ELEKTROMOTOR\n" +
                " Lieferdatum:      01.11.2022\n" +
                " Lagerort:         LOCATION1";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        System.out.println(hovercraft.print());
        ;
        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test
    void drive() {
        String expected = "Hallo ich bin ein Luftkissenboot und ich fahre auf dem Untergrund!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        hovercraft.drive();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test
    void swim() {
        String expected = "Hallo ich bin ein Luftkissenboot und ich fahre auf dem Wasser!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        hovercraft.swim();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }
}