package toys;

import administration.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JetTest {

    Address address = new Address("Steinstraße", "1a", 29664, "Bremen");
    Producer producer = new Producer("Toys4Us", address, 0516112345, "produktion@T4US.com");
    Jet jet = new Jet(UUID.randomUUID(),
            1,
            "Wum-Wum-Jet",
            Color.BLACK, Size.M,
            producer,
            10.00,
            15.00,
            SystemOfDrive.ELEKTROMOTOR,
            new Date(10, Calendar.NOVEMBER, 2022),
            StorageLocation.LOCATION1);

    @Test
    void fly_ShouldPrintExpectedText() {
        String expected = "Hallo ich bin ein Jet und ich fliege in der Luft!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        jet.fly();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }
}