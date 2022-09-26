package administration;

import org.junit.jupiter.api.Test;
import toy_features.*;
import toys.Sailboat;

import java.awt.*;
import java.util.*;

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

        assertThrows(InputMismatchException.class, () -> transformer.stringToColor(nonColorString));
    }

    @Test
    void colorToString_ColorBlackShouldReturnStringSchwarz() {
        Color blackAsColor = Color.black;

        String blackAsString = transformer.colorToString(blackAsColor);

        assertEquals("schwarz", blackAsString);
    }

    @Test
    void colorToString_RareColorShouldReturnException() {
        Color rareColor = new Color(50, 50, 50);

        assertThrows(InputMismatchException.class, () -> transformer.colorToString(rareColor));
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

        assertThrows(InputMismatchException.class, () -> transformer.stringToSize(sizeAsString));
    }

    @Test
    void stringToSystemOfDrive_1ShouldReturnDIESELMOTOR() {
        String numberOfSystem = "1"; //1 = Dieselmotor

        SystemOfDrive system = transformer.stringToSystemOfDrive(numberOfSystem);

        assertEquals(SystemOfDrive.DIESELMOTOR, system);
    }

    @Test
    void stringToSystemOfDrive_WrongInputShouldThrowException() {
        String input = "hallo";

        assertThrows(InputMismatchException.class, () -> transformer.stringToSystemOfDrive(input));
    }


    @Test
    void stringToDate_CorrectDateShouldReturnADate() {
        String dateAsString = "20.08.2020"; //dd.mm.yyyy
        Date testDate = new Date(2020 - 1900, Calendar.AUGUST, 20);

        Date actualDate = transformer.stringToDate(dateAsString);

        assertEquals(testDate, actualDate);
    }

    @Test
    void stringToDate_WrongInputShouldThrowException() {
        String input = "gestern";

        assertThrows(RuntimeException.class, () -> transformer.stringToDate(input));
    }

    @Test
    void dateToString_DateShouldReturnStringInRightFormat() {
        Date date = new Date(2022 - 1900, Calendar.DECEMBER, 24);

        String stringFromDate = transformer.dateToString(date);

        assertEquals("24.12.2022", stringFromDate);
    }

    @Test
    void dateToString_firsOfJanuaryShouldReturnTwoDigitNumbers() {
        Date date = new Date(2021 - 1900, Calendar.JANUARY, 1);

        String stringFromDate = transformer.dateToString(date);

        assertEquals("01.01.2021", stringFromDate);
    }

    @Test
    void stringToInteger_String1ShouldReturnInteger() {
        String number = "1";

        int integerFromString = transformer.stringToInteger(number);

        assertEquals(1, integerFromString);
    }

    @Test
    void stringToInteger_String999ShouldReturnInteger() {
        String number = "999";

        int integerFromString = transformer.stringToInteger(number);

        assertEquals(999, integerFromString);
    }

    @Test
    void stringToInteger_AlphaNumericStringShouldThrowException() {
        String number = "one111";

        assertThrows(RuntimeException.class, () -> transformer.stringToInteger(number));
    }

    @Test
    void stringToInteger_NumberWithinRangeShouldReturnInteger() {
        String number = "1";
        int maxInteger = 10;

        int integerFromString = transformer.stringToInteger(number, maxInteger);

        assertEquals(1, integerFromString);
    }

    @Test
    void stringToInteger_NumberOutOfRangeShouldThrowException() {
        String number = "11";
        int maxInteger = 10;

        assertThrows(RuntimeException.class, () -> transformer.stringToInteger(number, maxInteger));
    }

    @Test
    void stringToInteger_AlphaNumericStringWithRangeShouldThrowException() {
        String number = "one111";
        int maxInteger = 10;

        assertThrows(RuntimeException.class, () -> transformer.stringToInteger(number, maxInteger));
    }

    @Test
    void listToTable_InputShouldReturnTableInCorrectFormat() {
        ArrayList<String> fruits = new ArrayList<>(Arrays.asList("Apfel", "Birne", "Pflaume"));
        String expected = """
                1\t=\tApfel
                2\t=\tBirne
                3\t=\tPflaume
                Bitte die entsprechende Zahl eingeben""";

        String actual = transformer.listToMenuTable(fruits);

        assertEquals(expected, actual);
    }

    @Test
    void listToTable_EmptyListShouldThrowException() {
        ArrayList<String> noFruits = new ArrayList<>();

        assertThrows(RuntimeException.class, () -> transformer.listToMenuTable(noFruits));
    }

    @Test
    void integerToClass_1ShouldReturnClassSailboats() {
        Address address = new Address("Hauptstraße", "1a", 29664, "Bremen");
        Producer producer = new Producer("IKEA", address, "0516112345", "produktion@ikea.com");
        Date deliveryDate = new Date(2022-1900, Calendar.DECEMBER, 10);
        Sailboat sailboat = new Sailboat(UUID.randomUUID(),
                1,
                "Segelboot",
                Color.BLACK, Size.M,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                deliveryDate,
                StorageLocation.LOCATION1);

        Class<?> actualClass = transformer.integerToClass(1);

        assertEquals(sailboat.getClass(), actualClass);
    }

    @Test
    void integerToClass_999ShouldThrowException() {
        int integerOutOfRange = 999;
        assertThrows(RuntimeException.class, () -> transformer.integerToClass(integerOutOfRange));
    }




}