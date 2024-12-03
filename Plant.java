
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.*;

/**
 *
 * @author: Cameron Balderrama
 * @date: 11/25/2024
 */

public class Plant {

    public enum GrowthStage {
        Seedling, Vegetative, Flowering, Fruiting, Harvested
    }

    private int plantID;
    private String species;
    private float optimalTemperature;
    private float optimalHumidity;
    private GrowthStage stage;
    private EnvironmentLog log;

    // Constructor
    public Plant(int plantID, String species, float optimalTemperature, float optimalHumidity, GrowthStage stage, EnvironmentLog log) {
        this.plantID = plantID;
        this.species = species;
        this.optimalTemperature = optimalTemperature;
        this.optimalHumidity = optimalHumidity;
        this.stage = stage;
        this.log = log;

        // Log plant creation
        logPlantEvent("Added");
    }

    // Log plant-specific events
    private void logPlantEvent(String event) {
        String logEntry = "Plant ID: " + plantID + ", Species: " + species + ", Event: " + event;
        log.recordEnvironmentData("Plant Event", logEntry);
    }

    // Update growth stage
    public void updateGrowthStage() {
        switch (this.stage) {
            case Seedling:
                this.stage = GrowthStage.Vegetative;
                break;
            case Vegetative:
                this.stage = GrowthStage.Flowering;
                break;
            case Flowering:
                this.stage = GrowthStage.Fruiting;
                break;
            case Fruiting:
                this.stage = GrowthStage.Harvested;
                break;
            default:
                System.out.println("Plant has reached its final growth stage.");
        }

        // Log growth stage update
        log.recordEnvironmentData("Plant Event", "Plant ID: " + plantID + ", Species: " + species + ", Growth Stage Updated to " + stage);

    }

    public void setGrowthStage(GrowthStage stage) {
    this.stage = stage;
    }
    // Getters for plant details
     public int getPlantID() {
        return plantID;
     }

     public String getSpecies() {
        return species;
     }

    public GrowthStage getStage() {
        return stage;
    }

    public float getOptimalTemperature() {
        return optimalTemperature;
    }

    public float getOptimalHumidity() {
        return optimalHumidity;
    }

    @Override
    public String toString() {
        return "Plant [ID=" + plantID + ", Species=" + species + ", Growth Stage=" + stage + "]";
    }
}
