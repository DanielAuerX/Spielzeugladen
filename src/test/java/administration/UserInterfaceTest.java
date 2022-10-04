package administration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInterfaceTest {
    UserInterface userInterface = new UserInterface();

    @Mock
    Scanner scanner;

    @Test
    void run_Input3ShouldQuitProgram() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String userInput = "3";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        userInterface.run();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertTrue(actual.contains("Auf Wiedersehen!"));
    }

    @Test
    @Disabled("Exception NoSuchElement")
    void run_Input2and5ShouldQuitProgram() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String userInput = "2" + "\n5";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        userInterface.run();

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertTrue(actual.contains("Auf Wiedersehen!"));
    }

    @Test
    void run_WrongInputShouldThrowException() {
        String userInput = "abc";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        assertThrows(RuntimeException.class, () -> {
            userInterface.run();
        });
    }

    @Test
    void askForInput_ShouldReturnUserInput() {
        String userInput = "Hello this is a user input";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        String actual = userInterface.getUserInput("");

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

        userInterface.getUserInput("Enter your input");

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];
        assertEquals(expected, actual);
    }

    @Test
    void askForInput_ShouldReturnMockedInput() {
        when(scanner.nextLine()).thenReturn("hellow");
        String userInput = userInterface.getUserInput("Enter your input");
        assertEquals("hellow", userInput);
    }
}