package administration;

import java.util.Scanner;

public class UserInterface {
    public String askForParameter(String searchTerm){
        Scanner scanner = new Scanner(System.in);
        System.out.print(searchTerm+": ");
        return scanner.nextLine();
    }
}
