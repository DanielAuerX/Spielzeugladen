package administration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository {
    /*private ArrayList<Vehicle> getVehicles(){
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        JsonIO jsonIO = new JsonIO();
        String jsonString = jsonIO.readJson("R:\\Java\\Bankautomat\\customer_data.json");
        return gson.fromJson(jsonString, new TypeToken<ArrayList<Customer>>() {}.getType());
    }

     */

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
