package administration;

import toys.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {

    public void run() {
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

    public String askForInput(String outputText) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(outputText + ": ");
        return scanner.nextLine();
    }


}
