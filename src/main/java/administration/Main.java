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
        JsonIO jsonIO = new JsonIO();
        Repository repository = new Repository();
        Vehicle testVehicle = toyAdministration.createVehicle();
        System.out.println(testVehicle.getClass());
        System.out.println(testVehicle.print());
        testVehicle.printMovementOfVehicle(testVehicle);
        System.out.println(testVehicle.getSize());
        jsonIO.addVehicle(testVehicle);


        System.out.println(repository.getVehicleByExternalId(5).print());
        System.out.println(repository.getVehicleByExternalId(6).print());


        //Repository repo = new Repository();
        //repo.printProducer();


        //repository.printVehicle();



        //Vehicle vehicle = repository.getVehicleByExternalId(1);
        //System.out.println(vehicle.print());

        //Vehicle vehicleByExternalId = repository.getVehicleByExternalId(1);
        //System.out.println(vehicleByExternalId.print());

    }


}
