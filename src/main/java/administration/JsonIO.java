package administration;

import adapters.ColorAdapter;
import adapters.VehicleAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import toy_features.Producer;
import toys.Vehicle;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonIO {

    public String readJson(String filepath) {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public void addProducer(Producer producer){
        String filepath = "R:\\Java\\Spielzeugladen\\producer_data.json";
        Gson gson = new Gson();
        String jsonAsString = readJson(filepath);
        ArrayList<Producer> allProducers = gson.fromJson(jsonAsString, new TypeToken<ArrayList<Producer>>() {}.getType());
        allProducers.add(producer);
        String jsonText = gson.toJson(allProducers, new TypeToken<ArrayList<Producer>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeVehicleData(Vehicle vehicle, boolean shouldDelete){
        String filepath = "R:\\Java\\Spielzeugladen\\inventory_data.json";
        GsonBuilder builder = new GsonBuilder();                            //Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        builder.registerTypeAdapter(Vehicle.class, new VehicleAdapter());
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        Gson gson = builder.create();
        String jsonString = readJson(filepath);
        ArrayList<Vehicle> vehicles = gson.fromJson(jsonString, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        if (shouldDelete){
            vehicles.removeIf(i -> i.getInternalId().equals(vehicle.getInternalId()));
        }
        else {
            vehicles.add(vehicle);
        }
        String jsonText = gson.toJson(vehicles, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
