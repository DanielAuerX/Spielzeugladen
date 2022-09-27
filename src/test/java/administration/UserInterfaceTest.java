package administration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    UserInterface userInterface = new UserInterface();

    @Test
    @Disabled ("Not public anymore")
    void run_Input5ShouldQuitProgram() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String userInput = "5";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        //userInterface.runToyAdministration();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertTrue(actual.contains("Auf Wiedersehen!"));
    }

    @Test
    void askForInput_ShouldReturnUserInput() {
        String userInput = "Hello this is a user input";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String actual = userInterface.askForInput("");

        assertEquals("Hello this is a user input", actual);
    }

    @Test
    void askForInput_ShouldPrintOutputText() {
        String expected = "Enter your input: ";
        String userInput = " ";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);


        userInterface.askForInput("Enter your input");

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals(expected, actual);
    }
}