
/**
 * @author: Dalian Meraz
 * @date: 11/26/2024 
 */
import java.util.Scanner;
import java.util.List;

public class GreenhouseMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the greenhouse controller
        System.out.println("Welcome to the Automated Greenhouse System!");
        System.out.print("Enter initial temperature set point (°C): ");
        float tempSetPoint = scanner.nextFloat();
        System.out.print("Enter initial humidity set point (%): ");
        float humiditySetPoint = scanner.nextFloat();
        System.out.print("Enter initial light set point (lumens): ");
        int lightSetPoint = scanner.nextInt();
        System.out.print("Do you want to enable auto mode? (true/false): ");
        boolean isAutoMode = scanner.nextBoolean();

        GreenhouseController controller = new GreenhouseController(tempSetPoint, humiditySetPoint, lightSetPoint, isAutoMode);

        // Menu-driven system
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add a plant");
            System.out.println("2. Add a sensor");
            System.out.println("3. Add an actuator");
            System.out.println("4. Adjust environment settings");
            System.out.println("5. Monitor environment");
            System.out.println("6. View logs");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a plant
                    System.out.print("Enter plant ID: ");
                    int plantID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter plant species: ");
                    String species = scanner.nextLine();
                    System.out.print("Enter optimal temperature (°C): ");
                    float optimalTemp = scanner.nextFloat();
                    System.out.print("Enter optimal humidity (%): ");
                    float optimalHumidity = scanner.nextFloat();
                    Plant.GrowthStage stage = Plant.GrowthStage.Seedling; // Default stage
                    Plant newPlant = new Plant(plantID, species, optimalTemp, optimalHumidity, stage, controller.getEnvironmentLog());
                    controller.addPlant(newPlant);
                    System.out.println("Plant added successfully.");
                    
                    // Add the plant to the greenhouse
                    controller.addPlant(newPlant);
                    break;

                case 2:
                    // Add a sensor
                    System.out.print("Enter sensor ID: ");
                    int sensorID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter sensor type (TemperatureSensor): ");
                    String sensorType = scanner.nextLine();
                    System.out.print("Enter sensor threshold: ");
                    float threshold = scanner.nextFloat();
                    if (sensorType.equalsIgnoreCase("TemperatureSensor")) {
                        TemperatureSensor tempSensor = new TemperatureSensor(sensorID, threshold);
                        controller.addSensor(tempSensor);
                        System.out.println("Temperature sensor added successfully.");
                    } else {
                        System.out.println("Unknown sensor type.");
                    }
                    break;

                case 3:
                    // Add an actuator
                    System.out.print("Enter actuator ID: ");
                    int actuatorID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter actuator type (Fan): ");
                    String actuatorType = scanner.nextLine();
                    if (actuatorType.equalsIgnoreCase("Fan")) {
                        Fan fan = new Fan(actuatorID, false, "Cooling");
                        controller.addActuator(fan);
                        System.out.println("Fan added successfully.");
                    } else {
                        System.out.println("Unknown actuator type.");
                    }
                    break;

                case 4:
                    // Adjust environment settings
                    System.out.println("1. Adjust temperature");
                    System.out.println("2. Adjust humidity");
                    System.out.println("3. Adjust lighting");
                    System.out.print("Choose an option: ");
                    int settingChoice = scanner.nextInt();
                    if (settingChoice == 1) {
                        System.out.print("Enter new temperature set point (°C): ");
                        float newTemp = scanner.nextFloat();
                        controller.adjustTemperature(newTemp);
                    } else if (settingChoice == 2) {
                        System.out.print("Enter new humidity set point (%): ");
                        float newHumidity = scanner.nextFloat();
                        controller.adjustHumidity(newHumidity);
                    } else if (settingChoice == 3) {
                        System.out.print("Enter new light set point (lumens): ");
                        int newLight = scanner.nextInt();
                        controller.adjustLighting(newLight);
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case 5:
                    // Update sensor values dynamically via GreenhouseController
                    controller.updateSensorValues(scanner);

                    // Monitor environment after updating values
                    controller.monitorEnvironment();
                    break;


                case 6:
                    System.out.println("\nView Logs:");
                    System.out.println("1. View all logs");
                    System.out.println("2. View the latest log");
                    System.out.println("3. Filter logs by sensor type");
                    System.out.print("Choose an option: ");
                    int logChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    EnvironmentLog log = controller.getEnvironmentLog();

                    switch (logChoice) {
                    case 1:
                        log.displayLogs();// Display all logs
                        controller.displayPlants(); // Display all plants
                        break;
                        
                    case 2:
                    EnvironmentLog.LogEntry latestLog = log.getLatestLog(); // Get the latest log
                        if (latestLog != null) {
                            System.out.println("Latest Log: " + latestLog);
                        } else {
                            System.out.println("No logs available.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the sensor type to filter by: ");
                        String filterSensorType = scanner.nextLine();
                        List<EnvironmentLog.LogEntry> filteredLogs = log.filterLogsBySensorType(filterSensorType);
                        if (!filteredLogs.isEmpty()) {
                            for (EnvironmentLog.LogEntry entry : filteredLogs) {
                            System.out.println(entry);
                        }
                        } else {
                            System.out.println("No logs found for sensor type: " + filterSensorType);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 7:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
