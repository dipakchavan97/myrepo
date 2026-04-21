import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SimpleAlarmClock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Using a 24-hour format for simplicity
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("=== Simple Java Alarm Clock ===");
        System.out.print("Enter the alarm time (HH:mm:ss) in 24-hour format (e.g., 07:30:00): ");
        String alarmInput = scanner.nextLine();

        LocalTime alarmTime;
        try {
            // Parse the user input into a LocalTime object
            alarmTime = LocalTime.parse(alarmInput, formatter);
        } catch (Exception e) {
            System.out.println("Invalid time format. Please make sure to use HH:mm:ss.");
            scanner.close();
            return;
        }

        System.out.println("Alarm successfully set for: " + alarmTime);
        System.out.println("Waiting for the alarm...");

        boolean isRinging = false;

        // Loop until the alarm is triggered
        while (!isRinging) {
            LocalTime currentTime = LocalTime.now();

            // Check if the current time has reached or passed the alarm time
            if (currentTime.getHour() == alarmTime.getHour() && 
                currentTime.getMinute() == alarmTime.getMinute() && 
                currentTime.getSecond() >= alarmTime.getSecond()) {
                
                System.out.println("\n⏰ WAKE UP! ALARM RINGING! ⏰");
                
                // Triggers the default system beep sound
                java.awt.Toolkit.getDefaultToolkit().beep(); 
                
                isRinging = true;
            } else {
                try {
                    // Pause the loop for 1 second (1000 milliseconds) to prevent high CPU usage
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("The alarm was interrupted.");
                    break;
                }
            }
        }
        
        scanner.close();
    }
}
