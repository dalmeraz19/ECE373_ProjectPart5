import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.*;
/**
 *
 * @author (Nicholas Forster)
 * @version (10/14/24)
 */
public class Sensor //Sensor Class
{
    
    protected int ID; //sensor ID
    protected String type; //type of sensor
    protected float value; //value read by sensor
    protected float threshold; //value that will trigger an action if read by sensor (if sensor reading is above this value it will also trigger an action)
    protected Set<EnvironmentLog> logs; //environment logs owned by each sensor
    
    public Sensor() //sets default values
    {
        this.ID = 0;
        this.type = "";
        this.value = 0;
        this.threshold = 1000000;   //default threshold high to avoid accidental trigger when creating a new sensor
        this.logs = new HashSet<>();
    }
    
    public Sensor(int ID, String type, float threshold) //constructor class
    {
        this.ID = ID;
        this.type = type;
        this.threshold = threshold;
        logs = new HashSet<EnvironmentLog>();
    }
    
    public int getID() {
        return ID;
    }
    
    public String getType() {
        return type;
    }
    
    public float getValue() //in an infinite loop in main, acts as way to read values from sensor
    {
        return value;
    }
    
    public float getThreshold() {
        return threshold;
    }
    
    private void setID(int ID) {
        this.ID = ID;
    }
    
    private void setType(String type){
        this.type = type;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    public void setThreshold(float Threshold) //allows you to set the threshold depending on sensor type and plant type
    {
        this.threshold = threshold;
    }
    
    public void addLog(EnvironmentLog log) //Adds Environmental Logs to a Sensor
    {
        logs.add(log);
    }
    
    public Iterator<EnvironmentLog> iterator()
    {
        return logs.iterator();
    }

    // Check if the sensor reading exceeds the threshold
    public boolean shouldActuate() {
        return this.value >= this.threshold;
    }
    
    public String toString() //returns premade string describing the customer with the variables
    {
        String str;
        str = "Sensor ID: " + this.ID + "\nSensor Type: " + this.type + "\nSensor Reading: " + this.value + "\nSensor Threshold: " + this.threshold; 
        return str;
    }
}
