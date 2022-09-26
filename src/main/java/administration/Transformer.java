package administration;

import toy_features.Size;
import toy_features.SystemOfDrive;
import toys.Vehicle;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;

public class Transformer {
    public Color stringToColor(String colorAsString) {
        colorAsString = colorAsString.toLowerCase();
        Color colorFromString;
        switch (colorAsString) {
            case "rot" -> colorFromString = Color.red;
            case "grün" -> colorFromString = Color.green;
            case "blau" -> colorFromString = Color.blue;
            case "schwarz" -> colorFromString = Color.black;
            case "weiß" -> colorFromString = Color.white;
            case "gelb" -> colorFromString = Color.yellow;
            case "grau" -> colorFromString = Color.gray;
            default -> throw new InputMismatchException("Die Farbe konnte nicht gefunden werden!");
        }
        return colorFromString;
    }

    public String colorToString(Color color) {
        String colorAsString;
        if (Color.red.equals(color)) {
            colorAsString = "rot";
        } else if (Color.green.equals(color)) {
            colorAsString = "grün";
        } else if (Color.blue.equals(color)) {
            colorAsString = "blau";
        } else if (Color.black.equals(color)) {
            colorAsString = "schwarz";
        } else if (Color.white.equals(color)) {
            colorAsString = "weiß";
        } else if (Color.yellow.equals(color)) {
            colorAsString = "gelb";
        } else if (Color.gray.equals(color)) {
            colorAsString = "grau";
        } else {
            throw new InputMismatchException("Die Farbe konnte nicht gefunden werden!");
        }
        return colorAsString;
    }

    public Size stringToSize(String sizeAsString){
        sizeAsString = sizeAsString.toLowerCase();
        Size sizeFromString;
        switch (sizeAsString) {
            case "xl" -> sizeFromString = Size.XL;
            case "l" -> sizeFromString = Size.L;
            case "m" -> sizeFromString = Size.M;
            default -> throw new InputMismatchException("Die Größe konnte nicht gefunden werden!");
        }
        return sizeFromString;
    }

    public SystemOfDrive stringToSystemOfDrive(String systemAsString) {
        systemAsString = systemAsString.toLowerCase();
        SystemOfDrive systemFromString;
        switch (systemAsString) {
            case "1" -> systemFromString = SystemOfDrive.DIESELMOTOR;
            case "2" -> systemFromString = SystemOfDrive.BENZINMOTOR;
            case "3" -> systemFromString = SystemOfDrive.ELEKTROMOTOR;
            case "4" -> systemFromString = SystemOfDrive.KEROSINMOTOR;
            case "5" -> systemFromString = SystemOfDrive.GRUENE_ENERGIE;
            default -> throw new InputMismatchException("Diese Antriebsart konnte nicht gefunden werden!");
        }
        return systemFromString;
    }

    public Date stringToDate(String dateAsString) {
        Date date;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            date = format.parse(dateAsString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public String dateToString(Date date) {
        int day = date.getDate();
        int month = date.getMonth()+1;
        int year = date.getYear() + 1900;
        return String.format("%02d", day) + "." + String.format("%02d", month) + "." + year;
    }

    public int stringToInteger(String string, int maxSize) {
        int minSize = 1;
        try {
            int stringToInt = Integer.parseInt(string);
            if (stringToInt < minSize || stringToInt > maxSize) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Falsche Eingabe! Bitte eine der angegebenen Zahlen eingeben.");
            throw new RuntimeException(e);
        }
        return Integer.parseInt(string);
    }

    public int stringToInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Bitte ausschließlich Zahlen eingeben!");
            throw new RuntimeException(e);
        }
        return Integer.parseInt(string);
    }

    public String listToMenuTable(ArrayList<String> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("The list is empty!");
        }
        int counter = 1;
        StringBuilder returnText = new StringBuilder();
        for (String option : list) {
            returnText.append(counter).append("\t=\t").append(option).append("\n");
            counter++;
        }
        returnText.append("Bitte die entsprechende Zahl eingeben");
        return returnText.toString();
    }

    public String listToResultTable(ArrayList<Vehicle> searchResults) {
        String resultTable = "";
        if (searchResults.isEmpty()) {
            resultTable = "Es sind derzeit keine Artikel mit diesem Merkmal auf Lager.";
        } else {
            int counter = 1;
            resultTable += "Folgende Artikel wurden gefunden:\n";
            for (Vehicle vehicle : searchResults.stream().sorted(Comparator.comparing(Vehicle::getExternalId)).toList()) {
                resultTable += counter + ") " + "Artikelnummer " + vehicle.getExternalId() +
                        "\n\t" + " " + vehicle.getName() + " (" + "Lieferdatum: " + dateToString(vehicle.getDeliveryDate()) + "; " + "Lagerort: " + vehicle.getStorageLocation() + ")\n";
                counter++;
            }
        }
        return resultTable;
    }


    public Class<?> integerToClass(int choice) {
        try {
            if (1 == choice) {
                return Class.forName("toys.Sailboat");
            } else if (2 == choice) {
                return Class.forName("toys.Motorboat");
            } else if (3 == choice) {
                return Class.forName("toys.Submarine");
            } else if (4 == choice) {
                return Class.forName("toys.Hovercraft");
            } else if (5 == choice) {
                return Class.forName("toys.Bulldozer");
            } else if (6 == choice) {
                return Class.forName("toys.Motorcycle");
            } else if (7 == choice) {
                return Class.forName("toys.Car");
            } else if (8 == choice) {
                return Class.forName("toys.Truck");
            } else if (9 == choice) {
                return Class.forName("toys.Bicycle");
            } else if (10 == choice) {
                return Class.forName("toys.Helicopter");
            } else if (11 == choice) {
                return Class.forName("toys.Jet");
            } else if (12 == choice) {
                return Class.forName("toys.Glider");
            } else {
                throw new ClassNotFoundException("Typ nicht gefunden!");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
