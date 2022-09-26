package administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {

    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\033[0m";

    public void run(){
        Transformer transformer = new Transformer();
        ToyAdministration toyAdministration = new ToyAdministration();
        boolean quitProgram = false;
        ArrayList<String> options = new  ArrayList<>(Arrays.asList("Hinzufügen", "Bearbeiten", "Löschen", "Suchen", "Programm beenden"));
        String header = """
                ***** Spielzeug-Administration *****
                Was möchten Sie tun?
                """;
        while (!quitProgram){
            String choiceAsString = askForInput(header + transformer.listToMenuTable(options));
            int choice = transformer.stringToInteger(choiceAsString, options.size());
            switch (choice) {
                case 1 -> toyAdministration.add();
                case 2 -> System.out.println("edit");               //write edit
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
