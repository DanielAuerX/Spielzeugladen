package data;


public class Configuration {
    String producerPath;
    String inventoryPath;

    public Configuration(String producerPath, String inventoryPath) {
        this.producerPath = producerPath;
        this.inventoryPath = inventoryPath;
    }

    public String getProducerPath() {
        return producerPath;
    }

    public String getInventoryPath() {
        return inventoryPath;
    }



}
