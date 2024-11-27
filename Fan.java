import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.*;

/**
 * @author: David Sepulvida
 * @date: 10/14/2024 
 */

public class Fan extends Actuator {

    public Fan(int id, boolean status, String type) {
        this.actuatorID = id;
        this.status = status;
        this.type = type;
        this.threshold = threshold;
    }

    public void activate(){
        this.status = true;
        System.out.println("Fan activated");
    }

    public void deactivate(){
        this.status = false;
        System.out.println("Fan deactivated");
    }

    public void adjustSetting(int level) {
        this.threshold = level;
        System.out.println("Fan speed adjusted to level: " + level);
    }
    
}
    
