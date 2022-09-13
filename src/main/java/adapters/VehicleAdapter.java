
package adapters;

import administration.*;
import com.google.gson.*;
import toys.*;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.UUID;

public class VehicleAdapter implements JsonDeserializer<Vehicle> {


    @Override
    public Vehicle deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String genericName = jsonObject.get("genericName").getAsString();
        UUID internalId = jsonDeserializationContext.deserialize(jsonObject.get("internalId"), UUID.class);
        int externalId = jsonObject.get("externalId").getAsInt();
        String name = jsonObject.get("name").getAsString();
        Color color = jsonDeserializationContext.deserialize(jsonObject.get("color"), Color.class);
        Size size = jsonDeserializationContext.deserialize(jsonObject.get("size"), Size.class);
        Producer producer = jsonDeserializationContext.deserialize(jsonObject.get("producer"), Producer.class);
        double purchasePrice = jsonObject.get("purchasePrice").getAsDouble();
        double salesPrice = jsonObject.get("salesPrice").getAsDouble();
        SystemOfDrive systemOfDrive = jsonDeserializationContext.deserialize(jsonObject.get("systemOfDrive"), SystemOfDrive.class);
        Date deliveryDate = jsonDeserializationContext.deserialize(jsonObject.get("deliveryDate"), Date.class);
        StorageLocation storageLocation = jsonDeserializationContext.deserialize(jsonObject.get("storageLocation"), StorageLocation.class);

        if (jsonObject.get("numberOfWheels") != null) {
            if (genericName.contains("Auto")) {
                return new Car(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());}
            else if (genericName.contains("Motorrad")) {
                return new Motorcycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());}
            else if (genericName.contains("LKW")) {
                return new Truck(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());}
            else if (genericName.contains("Fahrrad")) {
                return new Bicycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());}
            else {throw new JsonParseException("No class has been found for this element!");}
        } else if (genericName.contains("Jet")) {
            return new Jet(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("Hubschrauber")) {
            return new Helicopter(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("Segelflugzeug")) {
            return new Glider(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("Segelboot")) {
            return new Sailboat(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("Motorboot")) {
            return new Motorboat(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("U-Boot")) {
            return new Submarine(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("Luftkissenboot")) {
            return new Hovercraft(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else if (genericName.contains("Bulldozer")) {
            return new Bulldozer(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation);
        } else {throw new JsonParseException("No class has been found for this element!");}
    }
}
