package administration;

import toy_features.StorageLocation;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<String> locationNames = Arrays.stream(StorageLocation.values()).map(StorageLocation::getLocationName).toList();
        System.out.println(locationNames);


        UserInterface userInterface = new UserInterface();
        userInterface.run();
    }
    }
