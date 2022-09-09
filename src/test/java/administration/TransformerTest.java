package administration;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class TransformerTest {
    Transformer transformer = new Transformer();

    @Test
    void stringToColor_StringRotShouldReturnColorRed() {
        String colorAsString = "rot";

        Color actual = transformer.stringToColor(colorAsString);

        assertEquals(actual, Color.red);
    }

    @Test
    void stringToColor_CapitalLetterRotShouldReturnColorRed() {
        String colorAsString = "Rot";

        Color redAsColor = transformer.stringToColor(colorAsString);

        assertEquals(redAsColor, Color.red);
    }

    @Test
    void stringToColor_nonColorStringShouldReturnException() {
        String nonColorString = "hallo";

        assertThrows(InputMismatchException.class, () -> {
            transformer.stringToColor(nonColorString);
        });
    }

    @Test
    void colorToString_ColorBlackShouldReturnStringSchwarz() {
        Color blackAsColor = Color.black;

        String blackAsString = transformer.colorToString(blackAsColor);

        assertEquals("schwarz", blackAsString);
    }

    @Test
    void colorToString_RareColorShouldReturnException() {
        Color rareColor = new Color(50,50,50);

        assertThrows(InputMismatchException.class, () -> {
            transformer.colorToString(rareColor);
        });
    }

    @Test
    void stringToSize_StringXLShouldReturnSizeXL() {
        String sizeAsString = "XL";

        Size size = transformer.stringToSize(sizeAsString);

        assertEquals(Size.XL, size);
    }

    @Test
    void stringToSize_NonExistingSizeShouldThrowException() {
        String sizeAsString = "XXXXXXXS";

        assertThrows(InputMismatchException.class, () -> {
            transformer.stringToSize(sizeAsString);
        });
    }

    @Test
    void stringToSystemOfDrive_1ShouldReturnDIESELMOTOR() {
        String numberOfSystem = "1"; //1 = Dieselmotor

        SystemOfDrive system = transformer.stringToSystemOfDrive(numberOfSystem);

        assertEquals(SystemOfDrive.DIESELMOTOR, system);
    }

    @Test
    void stringToSystemOfDrive_WrongInputShouldThrowException() {
        String input = "hallooooo";

        assertThrows(InputMismatchException.class, () -> {
            transformer.stringToSystemOfDrive(input);
        });
    }


    @Test
    void stringToDate_CorrectDateShouldReturnADate() {
        String dateAsString = "20.08.2020"; //dd.mm.yyyy
        Date testDate = new Date(2020-1900, Calendar.AUGUST, 20);

        Date actualDate = transformer.stringToDate(dateAsString);

        assertEquals(testDate, actualDate);
    }

    @Test
    void stringToDate_WrongInputShouldThrowException() {
        String input = "gestern";

        assertThrows(RuntimeException.class, () -> {
            transformer.stringToDate(input);
        });
    }
}