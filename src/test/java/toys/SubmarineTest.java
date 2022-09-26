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

class SubmarineTest {
    Address address = new Address("Hauptstraße", "1a", 29664, "Bremen");
    Producer producer = new Producer("IKEA", address, "0516112345", "produktion@ikea.com");
    Submarine submarine = new Submarine(UUID.randomUUID(),
            1,
            "U-Boot1",
            Color.BLACK, Size.M,
            producer,
            10.00,
            15.00,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(10, Calendar.NOVEMBER, 2022),
            StorageLocation.LOCATION1);

    @Test
    void dive_ShouldPrintExpectedDiveText() {
        String expected = "Hallo ich bin ein U-Boot und ich tauche im Wasser!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        submarine.dive();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test
    void swim_ShouldPrintExpectedSwimText() {
        String expected = "Hallo ich bin ein U-Boot und ich fahre auf dem Wasser!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        submarine.swim();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }
}