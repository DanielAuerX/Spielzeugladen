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

class SailboatTest {
    UUID uuid = UUID.randomUUID();
    Address address = new Address("Hauptstraße", "1a", 29664, "Bremen");
    Producer producer = new Producer("IKEA", address, "0516112345", "produktion@ikea.com");
    Date deliveryDate = new Date(10, Calendar.NOVEMBER, 2022);

    Sailboat sailboat = new Sailboat(uuid,
            1,
            "Segelboot-R2D2",
            Color.BLACK, Size.M,
            producer,
            10.00,
            15.00,
            SystemOfDrive.ELEKTROMOTOR,
            deliveryDate,
            StorageLocation.LOCATION1);

    @Test
    void swim() {
        String expected = "Hallo ich bin ein Segelboot und ich fahre auf dem Wasser!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        sailboat.swim();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }
}