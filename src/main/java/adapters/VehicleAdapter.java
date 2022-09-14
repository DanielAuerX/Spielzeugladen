
package adapters;

import administration.*;
import com.google.gson.*;
import toys.*;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.UUID;

public class VehicleAdapter implements JsonDeserializer<Vehicle>, JsonSerializer<Vehicle> {


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

        if (genericName.contains("Auto")) {
            return new Car(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());
        } else if (genericName.contains("Motorrad")) {
            return new Motorcycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());
        } else if (genericName.contains("LKW")) {
            return new Truck(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());
        } else if (genericName.contains("Fahrrad")) {
            return new Bicycle(internalId, externalId, name, color, size, producer, purchasePrice, salesPrice, systemOfDrive, deliveryDate, storageLocation, jsonObject.get("numberOfWheels").getAsInt());
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

    @Override
    public JsonElement serialize(Vehicle vehicle, Type typeOfVehicle, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("genericName", new JsonPrimitive(getGenericName(vehicle)));
        result.add("internalId", new JsonPrimitive(String.valueOf(vehicle.getInternalId())));
        result.add("externalId", new JsonPrimitive(vehicle.getExternalId()));
        result.add("name", new JsonPrimitive(vehicle.getName()));
        result.add("color", context.serialize(vehicle.getColor()));
        result.add("size", new JsonPrimitive(String.valueOf(vehicle.getSize())));
        result.add("producer", context.serialize(vehicle.getProducer()));
        result.add("purchasePrice", new JsonPrimitive(vehicle.getPurchasePrice()));
        result.add("salesPrice", new JsonPrimitive(vehicle.getSalesPrice()));
        result.add("systemOfDrive", new JsonPrimitive(String.valueOf(vehicle.getSystemOfDrive())));
        result.add("deliveryDate", context.serialize(vehicle.getDeliveryDate()));
        result.add("storageLocation", new JsonPrimitive(String.valueOf(vehicle.getStorageLocation())));
        if (vehicle instanceof Car || vehicle instanceof Bicycle || vehicle instanceof Motorcycle || vehicle instanceof Truck){
            result.add("numberOfWheels", new JsonPrimitive(getNumberOfWheels(vehicle)));
        }

        return result;
    }

    private String getGenericName(Vehicle vehicle){
        return switch (vehicle.getClass().getSimpleName()) {
            case "Submarine" -> "ein U-Boot";
            case "Car" -> "ein Auto";
            case "Bicycle" -> "ein Fahrrad";
            case "Bulldozer" -> "ein Bulldozer";
            case "Glider" -> "ein Segelflugzeug";
            case "Helicopter" -> "ein Hubschrauber";
            case "Hovercraft" -> "ein Luftkissenboot";
            case "Jet" -> "ein Jet";
            case "Motorboat" -> "ein Motorboot";
            case "Motorcycle" -> "ein Motorrad";
            case "Sailboat" -> "ein Segelboot";
            case "Truck" -> "ein LKW";
            default -> throw new InputMismatchException("No such class found!");
        };
    }

    private int getNumberOfWheels(Vehicle vehicle) {
        if (vehicle instanceof Car)
            return ((Car) vehicle).getNumberOfWheels();
        else if (vehicle instanceof Bicycle)
            return ((Bicycle) vehicle).getNumberOfWheels();
        else if (vehicle instanceof Motorcycle)
            return ((Motorcycle) vehicle).getNumberOfWheels();
        else if (vehicle instanceof Truck)
            return ((Truck) vehicle).getNumberOfWheels();
        else {
            throw new InputMismatchException("Vehicle has no wheels or has not been found");
        }
    }


}
