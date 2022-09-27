package administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {

    public void run(){
        Transformer transformer = new Transformer();
        String startText = """
                Welches Programm möchten Sie starten?
                1   =   Spielzeug-Administration
                2   =   Lagerort-Administration
                Bitte die entsprechende Zahl eingeben""";
        int input = transformer.stringToInteger(askForInput(startText), 2);
        switch (input) {
            case 1 -> runToyAdministration();
            case 2 -> runStorageAdministration();
        }
    }

    private void runToyAdministration() {
        Transformer transformer = new Transformer();
        ToyAdministration toyAdministration = new ToyAdministration();
        boolean quitProgram = false;
        ArrayList<String> options = new ArrayList<>(Arrays.asList("Hinzufügen", "Bearbeiten", "Löschen", "Suchen", "Programm beenden"));
        String header = "\u001B[1m" + "***** Spielzeug-Administration *****\n" + "\033[0m" + "Was möchten Sie tun?\n";
        while (!quitProgram) {
            String choiceAsString = askForInput(header + transformer.listToMenuTable(options));
            int choice = transformer.stringToInteger(choiceAsString, options.size());
            switch (choice) {
                case 1 -> {
                    toyAdministration.add();
                    System.out.println("Der Artikel wurde hinzugefügt.");
                }
                case 2 -> {
                    toyAdministration.edit();
                    System.out.println("Der Artikel wurde bearbeitet.");
                }
                case 3 -> {
                    toyAdministration.delete();
                    System.out.println("Der Artikel wurde gelöscht.");
                }
                case 4 -> System.out.println(toyAdministration.find());
                case 5 -> {
                    System.out.println("Auf Wiedersehen!");
                    quitProgram = true;
                }
            }
        }
    }

    private void runStorageAdministration(){
        var transformer = new Transformer();
        var storageAdministration = new StorageAdministration();
        boolean quitProgram = false;
        var options = new ArrayList<>(Arrays.asList("Artikel anzeigen", "Lagerort ändern", "Lieferort ändern", "Programm beenden"));
        String header = "\u001B[1m" + "***** Lagerort-Administration *****\n" + "\033[0m" + "Was möchten Sie tun?\n";
        while (!quitProgram) {
            String choiceAsString = askForInput(header + transformer.listToMenuTable(options));
            int choice = transformer.stringToInteger(choiceAsString, options.size());
            switch (choice) {
                case 1 -> {
                    System.out.println(storageAdministration.find());
                }
                case 2 -> {}
                case 3 -> {}
                case 4 -> {
                    System.out.println("Auf Wiedersehen!");
                    quitProgram = true;
                }
            }
        }
    }

    public String askForInput(String outputText) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(outputText + ": ");
        return scanner.nextLine();
    }


}
