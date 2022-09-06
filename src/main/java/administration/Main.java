package administration;

import toys.Sailboat;
import toys.Submarine;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args){

        UUID uuid = UUID.randomUUID();
        Address address = new Address("Hauptstraße", "1a", 29664, "Bremen");
        Producer producer = new Producer("IKEA", address, 0516112345, "produktion@ikea.com");
        Date deliveryDate = new Date(10, Calendar.NOVEMBER, 2022);

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
        segelboot.swim();

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
        uboot.dive();

        System.out.println(uboot);

    }
}
