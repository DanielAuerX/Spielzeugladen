package administration;

import adapters.ColorAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
        String accountFilepath = "R:\\Java\\Spielzeugladen\\producer_data.json";
        Gson gson = new Gson();
        String jsonAsString = readJson(accountFilepath);
        ArrayList<Producer> allProducers = gson.fromJson(jsonAsString, new TypeToken<ArrayList<Producer>>() {}.getType());
        allProducers.add(producer);
        String jsonText = gson.toJson(allProducers, new TypeToken<ArrayList<Producer>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountFilepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle(Vehicle vehicle){
        String accountFilepath = "R:\\Java\\Spielzeugladen\\vehicle_data_test.json";
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Color.class, new ColorAdapter());
        Gson gson = builder.create();
        //String jsonAsString = readJson(accountFilepath);
        //ArrayList<Vehicle> vehicles = gson.fromJson(jsonAsString, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        String jsonText = gson.toJson(vehicles, new TypeToken<ArrayList<Vehicle>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountFilepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
