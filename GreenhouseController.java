import java.util.*;
/**
 * @author: Dalian Meraz
 * @date: 10/14/2024 
 */


public class GreenhouseController {
    // Attributes
    private float temperatureSetPoint;
    private float humiditySetPoint;
    private int lightSetPoint;
    private boolean isAutoMode;
    private List<Sensor> sensors;
    private List<Actuator> actuators;
    private List<Plant> plants;
    private EnvironmentLog environmentLog;

    // Constructor
    public GreenhouseController(float temperatureSetPoint, float humiditySetPoint, int lightSetPoint, boolean isAutoMode) {
        this.temperatureSetPoint = temperatureSetPoint;
        this.humiditySetPoint = humiditySetPoint;
        this.lightSetPoint = lightSetPoint;
        this.isAutoMode = isAutoMode;
        this.sensors = new ArrayList<>();
        this.actuators = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.environmentLog = new EnvironmentLog();
    }

    // Method to update sensor values dynamically
    public void updateSensorValues(Scanner scanner) {
        System.out.println("\nUpdating Sensor Values:");
        if (sensors.isEmpty()) {
            System.out.println("No sensors available to update.");
            return;
        }

        for (Sensor sensor : sensors) {
            System.out.println("Current value for " + sensor.getType() + " (ID: " + sensor.getID() + "): " + sensor.getValue());
            System.out.print("Enter new value for " + sensor.getType() + ": ");
            float newValue = scanner.nextFloat();
            sensor.setValue(newValue); // Update the sensor's value
            System.out.println(sensor.getType() + " value updated to " + newValue);
        }
    }

    // Method to monitor the environment
    public void monitorEnvironment() {
        for (Sensor sensor : sensors) {
            float sensorValue = sensor.getValue(); // Get the current sensor value
            String sensorValueString = String.valueOf(sensorValue);
            environmentLog.recordEnvironmentData(sensor.getType(), sensorValueString); // Log the data
            System.out.println(sensor.getType() + " reading: " + sensorValue);

            // Automatic control of actuators
            if (isAutoMode) {
                controlActuators(sensor);
            }
        }
    }

    // Private method to control actuators based on sensor readings
    private void controlActuators(Sensor sensor) {
        if (sensor instanceof TemperatureSensor) {
            float sensorValue = sensor.getValue();
            if (sensorValue > temperatureSetPoint) {
                for (Actuator actuator : actuators) {
                    if (actuator instanceof Fan) {
                        actuator.activate();
                        System.out.println("Fan activated to reduce temperature.");
                    }
                }
            }
        }
        // Additional logic for other sensor types can go here
    }

    // Methods to add components
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void addActuator(Actuator actuator) {
        actuators.add(actuator);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
        environmentLog.recordEnvironmentData("Plant Event", "Plant ID: " + plant.getPlantID() + ", Species: " + plant.getSpecies());
        System.out.println("Plant added: " + plant.getSpecies());
    }

    // Getters for sensors and actuators
    public List<Sensor> getSensors() {
        return sensors;
    }

    public List<Actuator> getActuators() {
        return actuators;
    }

    // Methods to adjust environment settings
    public void adjustTemperature(float newTemp) {
        this.temperatureSetPoint = newTemp;
        System.out.println("Temperature set to " + newTemp + " degrees.");
    }

    public void adjustHumidity(float newHumidity) {
        this.humiditySetPoint = newHumidity;
        System.out.println("Humidity set to " + newHumidity + "%.");
    }

    public void adjustLighting(int newLightLevel) {
        this.lightSetPoint = newLightLevel;
        System.out.println("Light Level set to " + newLightLevel + " lumens.");
    }

    // Method to toggle auto mode
    public void switchAutoMode(boolean status) {
        this.isAutoMode = status;
        System.out.println("Auto mode set to " + (status ? "ON" : "OFF"));
    }

    // Display all plants
    public void displayPlants() {
        if (plants.isEmpty()) {
            System.out.println("No plants in the greenhouse.");
        } else {
            System.out.println("\nPlants in the Greenhouse:");
            for (Plant plant : plants) {
                System.out.println(plant);
            }
        }
    }
    
    // Update a plant's growth stage
    public void updatePlantGrowth(int plantID) {
        for (Plant plant : plants) {
            if (plant.getPlantID() == plantID) {
                plant.updateGrowthStage();
                environmentLog.recordEnvironmentData("Plant Event", "Plant ID: " + plantID + ", Species: " + plant.getSpecies() + " growth stage updated to " + plant.getStage());
                System.out.println("Plant " + plant.getSpecies() + " growth stage updated to " + plant.getStage());
                return;
            }
        }
        System.out.println("Plant with ID " + plantID + " not found.");
    }


    // Optional: Display actuators
    public void displayActuators() {
        if (actuators.isEmpty()) {
            System.out.println("No actuators added yet.");
        } else {
            for (Actuator actuator : actuators) {
                System.out.println("Actuator ID: " + actuator.getActuatorID() + ", Type: " + actuator.getType());
            }
        }
    }
    
    // Getter for the environment log
    public EnvironmentLog getEnvironmentLog() {
        return environmentLog;
    }

}

    
