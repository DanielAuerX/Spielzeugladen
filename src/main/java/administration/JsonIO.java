package administration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
}
