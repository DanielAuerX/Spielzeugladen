package administration;

import toys.*;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args){

        UUID uuid = UUID.randomUUID();
        Address address = new Address("Hauptstraße", "1a", 29664, "Bremen");
        Producer producer = new Producer("IKEA", address, "0516112345", "produktion@ikea.com");
        Date deliveryDate = new Date(2022-1900, 11, 10);

        Sailboat segelboot = new Sailboat(uuid,
                1,
                "Segelboot",
                Color.BLACK, Size.M,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                deliveryDate,
                StorageLocation.LOCATION1);
        //segelboot.swim();

        Submarine uboot = new Submarine(uuid,
                1,
                "U-Boot",
                Color.BLACK, Size.M,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                deliveryDate,
                StorageLocation.LOCATION1);
        //uboot.dive();

        //System.out.println(uboot.print());


        Car car = new Car(UUID.randomUUID(),
                1,
                "Speedo",
                Color.BLACK, Size.M,
                producer,
                10.00,
                15.00,
                SystemOfDrive.ELEKTROMOTOR,
                new Date(2022-1900, Calendar.NOVEMBER, 15),
                StorageLocation.LOCATION1,
                4);
        //System.out.println(car.print());

        ToyAdministration toyAdministration = new ToyAdministration();
        //Vehicle testVehicle = toyAdministration.createVehicle();
        //System.out.println(testVehicle.getClass());
        //testVehicle.printMovementOfVehicle(testVehicle);

        //Repository repo = new Repository();
        //repo.printProducer();

        Repository repository = new Repository();
        repository.printVehicle();

        JsonIO jsonIO = new JsonIO();
        //jsonIO.addVehicle(testVehicle);




    }


}
