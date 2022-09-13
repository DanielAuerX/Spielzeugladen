package administration;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

import adapters.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import toys.Vehicle;

public class Repository {
    private ArrayList<Producer> instantiateProducers(){
        Gson gson = new Gson();
        JsonIO jsonIO = new JsonIO();
        String jsonString = jsonIO.readJson("R:\\Java\\Spielzeugladen\\producer_data.json");
        ArrayList<Producer> producers = gson.fromJson(jsonString, new TypeToken<ArrayList<Producer>>() {}.getType());
        return producers;
    }

    private ArrayList<Vehicle> instantiateVehicles(){
        GsonBuilder builder = new GsonBuilder();                            //Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        builder.registerTypeAdapter(Vehicle.class, new VehicleAdapter());
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        Gson gson = builder.create();
        JsonIO jsonIO = new JsonIO();
        String jsonString = jsonIO.readJson("R:\\Java\\Spielzeugladen\\vehicle_data_test.json");
        ArrayList<Vehicle> vehicles = gson.fromJson(jsonString, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        return vehicles;
    }


    public void printVehicle(){
        ArrayList<Vehicle> vehicles = instantiateVehicles();
        System.out.println(vehicles.get(0).getClass());
        System.out.println(vehicles.get(0).print());
        System.out.println(vehicles.get(1).getClass());
        System.out.println(vehicles.get(1).print());

    }

    /*



    private HashMap<String, Producer> getProducerHashMap (){
        ArrayList<Producer> producers = getProducers();
        HashMap<String, Producer> producersMap = new HashMap<>();
        for (Producer producer : producers){
            producersMap.put(producer.getName(), producer);
        }
        return producersMap;
    }

    public Producer getProducerByName(String name){
        HashMap<String , Producer> producers = getProducerHashMap();
        return producers.get(name);
    }

     */

    public Producer getProducer(String indexAsString){
        int index = Integer.parseInt(indexAsString);
        ArrayList<Producer> producers = instantiateProducers();
        JsonIO jsonIO = new JsonIO();
        ToyAdministration toyAdministration = new ToyAdministration();
        Producer producer;
        if(index==0){               //customer chose to create new Producer
            producer = toyAdministration.createProducer();
            jsonIO.addProducer(producer);
        }
        else if (index > 0 && index < producers.size()+1) {
            producer = producers.get(index - 1);
        }
        else {
            throw new InputMismatchException("Falsche Eingabe!");
        }
        return producer;
    }

    public String getProducerNames(){
        ArrayList<Producer> producers = instantiateProducers();
        int counter = 1;
        String producerNames = "Hersteller:\n0\t=\tneuen Hersteller anlegen\n";
        for (Producer producer : producers) {
            producerNames += counter+"\t=\t"+producer.getName()+"\n";
            counter++;
        }
        producerNames += "Bitte die entsprechende Zahl eingeben";
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
