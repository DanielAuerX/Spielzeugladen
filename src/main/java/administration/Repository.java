package administration;

import java.awt.*;
import java.util.*;

import adapters.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import toy_features.Producer;
import toy_features.Size;
import toy_features.SystemOfDrive;
import toys.Vehicle;

public class Repository {
    private ArrayList<Producer> instantiateProducers(){
        Gson gson = new Gson();
        JsonIO jsonIO = new JsonIO();
        String jsonString = jsonIO.readJson("R:\\Java\\Spielzeugladen\\producer_data.json");
        ArrayList<Producer> producers = gson.fromJson(jsonString, new TypeToken<ArrayList<Producer>>() {}.getType());
        return producers;
    }

    private HashMap<UUID, Vehicle> instantiateVehicles(){
        GsonBuilder builder = new GsonBuilder();                            //Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        builder.registerTypeAdapter(Vehicle.class, new VehicleAdapter());
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        Gson gson = builder.create();
        JsonIO jsonIO = new JsonIO();
        String jsonString = jsonIO.readJson("R:\\Java\\Spielzeugladen\\inventory_data.json");
        ArrayList<Vehicle> vehicles = gson.fromJson(jsonString, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        HashMap<UUID, Vehicle> vehicleHashMap = new HashMap<>();
        for (Vehicle vehicle : vehicles){
            vehicleHashMap.put(vehicle.getInternalId(), vehicle);
        }
        return vehicleHashMap;
    }

    private Vehicle getVehicleByInternalId(UUID internalId){
        HashMap<UUID , Vehicle> vehicles = instantiateVehicles();
        return vehicles.get(internalId);
    }

    private UUID getInternalID(int externalID){
        HashMap<Integer, UUID> idHashMap = new HashMap<>();
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        for (UUID i: vehicleHashMap.keySet()) {
            Integer key = vehicleHashMap.get(i).getExternalId();
            idHashMap.put(key, i);
        }
        UUID internalId = idHashMap.get(externalID);
        if (internalId == null){
            throw new InputMismatchException("Diese Artikelnummer konnte nicht gefunden werden!");
        }
        return internalId;
    }

    public Vehicle getVehicleByExternalId(int externalId){
        UUID internalID = getInternalID(externalId);
        return getVehicleByInternalId(internalID);
    }

    public Vehicle getVehicleByName(String name){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        for (UUID i: vehicleHashMap.keySet()) {
            if (name.equalsIgnoreCase(vehicleHashMap.get(i).getName())){
                return vehicleHashMap.get(i);
            }
        }
        throw new InputMismatchException("Der Name konnte nicht gefunden werden!");
    }

    public ArrayList<Vehicle> getVehiclesByProducer(Producer producer){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        ArrayList<Vehicle> vehiclesOfProducer = new ArrayList<>();
        for (UUID i: vehicleHashMap.keySet()) {
            if (producer.getName().equals(vehicleHashMap.get(i).getProducer().getName())){
                vehiclesOfProducer.add(vehicleHashMap.get(i));
            }
        }
        return vehiclesOfProducer;
    }

    public ArrayList<Vehicle> getVehiclesByClass(Class<?> type){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        ArrayList<Vehicle> vehiclesOfType = new ArrayList<>();
        for (UUID i: vehicleHashMap.keySet()) {
            if (type == vehicleHashMap.get(i).getClass()){
                vehiclesOfType.add(vehicleHashMap.get(i));
            }
        }
        return vehiclesOfType;
    }

    public ArrayList<Vehicle> getVehiclesByColor(Color color){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        ArrayList<Vehicle> vehiclesOfColor = new ArrayList<>();
        for (UUID i: vehicleHashMap.keySet()) {
            if (color.equals(vehicleHashMap.get(i).getColor())){
                vehiclesOfColor.add(vehicleHashMap.get(i));
            }
        }
        return vehiclesOfColor;
    }

    public ArrayList<Vehicle> getVehiclesBySize(Size size){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        ArrayList<Vehicle> vehiclesOfSize = new ArrayList<>();
        for (UUID i: vehicleHashMap.keySet()) {
            if (size.equals(vehicleHashMap.get(i).getSize())){
                vehiclesOfSize.add(vehicleHashMap.get(i));
            }
        }
        return vehiclesOfSize;
    }

    public ArrayList<Vehicle> getVehiclesBySystemOfDrive(SystemOfDrive systemOfDrive){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        ArrayList<Vehicle> vehiclesOfSystem = new ArrayList<>();
        for (UUID i: vehicleHashMap.keySet()) {
            if (systemOfDrive.equals(vehicleHashMap.get(i).getSystemOfDrive())){
                vehiclesOfSystem.add(vehicleHashMap.get(i));
            }
        }
        return vehiclesOfSystem;
    }

    public int getHighestExternalId(){
        HashMap<UUID, Vehicle> vehicleHashMap = instantiateVehicles();
        ArrayList<Integer> list = new ArrayList<>();
        for (UUID i: vehicleHashMap.keySet()) {
            list.add(vehicleHashMap.get(i).getExternalId());
        }
        return Collections.max(list);
    }

    public Producer getProducer(int index) {
        ArrayList<Producer> producers = instantiateProducers();
        Producer producer;
        if (index > 0 && index < producers.size() + 1) {
            producer = producers.get(index - 1);
        } else {
            throw new InputMismatchException("Falsche Eingabe!");
        }
        return producer;
    }

    public ArrayList<String> getProducerNames(){
        ArrayList<Producer> producers = instantiateProducers();
        ArrayList<String> producerNames = new ArrayList<>();
        for (Producer producer : producers) {
            producerNames.add(producer.getName());
        }
        return producerNames;
    }



    /*public Optional<Vehicle> getVehicle(String vehicleID){
        ArrayList<Vehicle> allVehicles = getVehicles();
        List<Vehicle> customers = allVehicles.stream().
                filter(customer -> customer.getId().equals(validCustomerID)).     // customer.getId().compareTo(validCustomerID) == 0     compareTo retruns -1 (less than), 0 (equals) or 1 (greater than)
                        toList();
        //stream for each lambda
        // hashmap
        return Optional<customers.get(0)>;
    }

     */


}
