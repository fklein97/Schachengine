
package UCI;
import Parameters.Parameters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *  Class Input/Output managing all communication tasks
 */
public class IO {

    private final String BLANK = " ";
    private final String EMPTY = "";
    private final String FILENAME = "";

    private String input;
    private String command;
    private Scanner scann;
    private static FileWriter writer = null;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
    private static LocalDateTime now = LocalDateTime.now();


    /**
     * Constructor to set up the scanner
     */
    public IO(){
            input = EMPTY;
            command = EMPTY;
            scann = new Scanner(System.in);
            newLogFile();
    }

    /**
     * Method to read a full line from System.in
     */
    public String receive() {
        input = scann.nextLine();
        LocalDateTime writetime = LocalDateTime.now();
        log(writetime.getHour() + ":" + writetime.getMinute()+ ":" + writetime.getSecond() + " ---> : " + input);
        return input;
    }

    /**
     * Method to answer the GUI via std.out
     * @param output String that is send to GUI
     */
    public static void answer(String output){
        LocalDateTime writetime = LocalDateTime.now();
        log(writetime.getHour() + ":" + writetime.getMinute()+ ":" + writetime.getSecond() + " <--- : " + output);
        System.out.println(output);
    }

    public static void sendDebugInfo(String output){
        String debugOutput = "INFO DEBUG: " + output;
        if(Parameters.debugMode == true){
            System.out.println(debugOutput);
        }
        LocalDateTime writetime = LocalDateTime.now();
        log(writetime.getHour() + ":" + writetime.getMinute()+ ":" + writetime.getSecond() + " <--- : " + debugOutput);
    }

    /**
     * Method to log messages
     * @param input message
     */
    public static void log(String input){
        try {
            if (writer == null) {
                newLogFile();
            }
            writer = new FileWriter("log_" + now.format(dtf) + ".log", true);
            writer.write(input + "\n");
            if(writer != null){
                writer.flush();
                writer.close();
            }
        }
        catch(IOException e){
            System.out.println("ERROR: couldnt write log");
        }
    }

    public static void newLogFile(){
        try {
            if(writer != null){
                writer.flush();
                writer.close();
            }
            now = LocalDateTime.now();
            writer = new FileWriter("log_" + now.format(dtf) + ".log", true);
        }
        catch(IOException e){
            System.out.println("ERROR: couldnt create a new Logfile");
        }
    }

    /**
     * @param input full line of input
     * @return just the command send by the GUI
     */
    public String commandCutter(String input) {

        String output;
        if (input.contains(BLANK)) {
            output = input.substring(0, input.indexOf(BLANK));
        } else {
            output = input;
        }
        return output;
    }

}
