import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @author: Dalian Meraz
 * @date: 10/14/2024 
 */

public class EnvironmentLog {

    // Inner class to represent a single log entry
    public class LogEntry {
        private LocalDateTime timestamp;
        private String sensorType;
        private String description;

        public LogEntry(String sensorType, String description) {
            this.timestamp = LocalDateTime.now(); // Automatically set the timestamp
            this.sensorType = sensorType;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Timestamp: " + timestamp + ", Sensor Type: " + sensorType + ", Detials: " + description;
        }
    }

    // List to hold all log entries
    private List<LogEntry> entries;

    // Constructor
    public EnvironmentLog() {
        this.entries = new ArrayList<>();
    }

    // Method to record environmental data
    public void recordEnvironmentData(String sensorType, String description) {
        LogEntry entry = new LogEntry(sensorType, description);
        entries.add(entry);
    }

    // Method to display all log entries
    public void displayLogs() {
        if (entries.isEmpty()) {
            System.out.println("No logs available.");
        } else {
            System.out.println("\nEnvironment Logs:");
            for (LogEntry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    // Method to get the latest log entry
    public LogEntry getLatestLog() {
        if (entries.isEmpty()) {
            return null;
        }
        return entries.get(entries.size() - 1);
    }

    // Method to filter logs by sensor type
    public List<LogEntry> filterLogsBySensorType(String sensorType) {
        List<LogEntry> filteredLogs = new ArrayList<>();
        for (LogEntry entry : entries) {
            if (entry.sensorType.equalsIgnoreCase(sensorType)) {
                filteredLogs.add(entry);
            }
        }
        return filteredLogs;
    }
}


