import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.*;
/**
 * @author: David Sepulvida
 * @date: 10/14/2024 
 */

abstract class Actuator {
    protected int actuatorID;
    protected int threshold;
    protected boolean status;
    protected String type;

    //abstract methods
    public abstract void activate();
    public abstract void deactivate();
    public abstract void adjustSetting(int level);

    
    // Getters and Setters
    public int getActuatorID() {
        return actuatorID;
    }

    public void setActuatorID(int actuatorID) {
        this.actuatorID = actuatorID;
    }

    //Threshold
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    //Status
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
 

