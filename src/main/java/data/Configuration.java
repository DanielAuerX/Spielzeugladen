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

    /*
    public static String producerPath;
    public static String inventoryPath;

    public Configuration(String producerPath, String inventoryPath) {
        Configuration.producerPath = producerPath;
        Configuration.inventoryPath = inventoryPath;
    }

     */



}
