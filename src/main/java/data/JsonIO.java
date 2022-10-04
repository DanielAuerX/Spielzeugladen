package data;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

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

    public void addProducer(Producer producer, String filepath) {
        Gson gson = new Gson();
        String jsonAsString = readJson(filepath);
        ArrayList<Producer> allProducers;
        if (!jsonAsString.equals("")) {
            allProducers = gson.fromJson(jsonAsString, new TypeToken<ArrayList<Producer>>() {
            }.getType());
            allProducers.add(producer);
        }
        else {
            allProducers = new ArrayList<>(Collections.singletonList(producer));
        }
        String jsonText = gson.toJson(allProducers, new TypeToken<ArrayList<Producer>>() {
        }.getType());
        writeToJson(filepath, jsonText);
    }

    public void writeVehicleToData(Vehicle vehicle, String filepath) {
        GsonBuilder builder = new GsonBuilder();                            //Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        builder.registerTypeAdapter(Vehicle.class, new VehicleAdapter());
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        Gson gson = builder.create();
        String jsonAsString = readJson(filepath);
        ArrayList<Vehicle> vehicles;
        if (!jsonAsString.equals("")) { //if file contains vehicles -> check if vehicle already exits
            vehicles = gson.fromJson(jsonAsString, new TypeToken<ArrayList<Vehicle>>() {}.getType());
            vehicles.removeIf(i -> i.getInternalId().equals(vehicle.getInternalId()));
            vehicles.add(vehicle);
        }
        else {  //if file is empty
            vehicles = new ArrayList<>(Collections.singletonList(vehicle));
        }
        String jsonText = gson.toJson(vehicles, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        writeToJson(filepath, jsonText);
    }


    public void deleteVehicleFromData(Vehicle vehicle, String filepath) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Vehicle.class, new VehicleAdapter());
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        Gson gson = builder.create();
        String jsonString = readJson(filepath);
        ArrayList<Vehicle> vehicles = gson.fromJson(jsonString, new TypeToken<ArrayList<Vehicle>>() {
        }.getType());
        vehicles.removeIf(i -> i.getInternalId().equals(vehicle.getInternalId()));
        String jsonText = gson.toJson(vehicles, new TypeToken<ArrayList<Vehicle>>() {
        }.getType());
        writeToJson(filepath, jsonText);
    }

    private static void writeToJson(String filepath, String jsonText) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(jsonText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
