package administration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    Repository repository = new Repository();

    @Test
    void getProducer_1ShouldReturnProducer() {
        Address address = new Address("Hauptstraße", "1a", 29664, "Bremen");
        Producer testProducer = new Producer("IKEA", address, "0516112345", "produktion@ikea.com");

        Producer producer = repository.getProducer("1");

        assertEquals(testProducer.getClass(), producer.getClass());
    }

    @Test
    @Disabled("Test runs for ever")
    void getProducer_0ShouldReturnStartCreateProducer() {
        String expected = "Name des Herstellers: ";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        repository.getProducer("0");

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals(expected, actual);
    }

    @Test
    void getProducer_OutOfBoundIndexShouldThrowException() {
        assertThrows(InputMismatchException.class, () -> {
            repository.getProducer("9999999");
        });
    }

    @Test
    void getProducer_AlphaNumericShouldThrowException() {
        assertThrows(NumberFormatException.class, () -> {
            repository.getProducer("abc999");
        });
    }

    @Test
    @Disabled("Throws exception")
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
}