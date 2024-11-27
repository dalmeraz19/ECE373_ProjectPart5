/**
 * @author: Dalian Meraz
 * @date: 10/14/2024 
 */

public class TemperatureSensor extends Sensor {
    // Additional attributes specific to TemperatureSensor can be added here

    // Constructor
    public TemperatureSensor(int ID, float threshold) {
        super(ID, "TemperatureSensor", threshold); // Call to Sensor constructor
    }

    // Override or add specific methods if necessary
    
    public float readValue() {
        // Implement the logic to read the temperature value from the sensor
        // For now, let's return a dummy value (you would replace this with actual sensor reading logic)
        return getValue(); // assuming getValue() returns the last read value
    }

    public String toString() {
        return "TemperatureSensor [ID=" + getID() + ", Threshold=" + getThreshold() + "]";
    }
}
