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
    private BufferedWriter writer;

    /**
     * Constructor to set up the scanner
     */
    public IO(){
            input = EMPTY;
            command = EMPTY;
            scann = new Scanner(System.in);
            try {
                writer = new BufferedWriter(new FileWriter(FILENAME));
            } catch (IOException e){
                //TODO FEHLERMELDUNG LOG KANN NICHT GEÖFNNET WERDEN
            }

    }

    /**
     * Method to read a full line from System.in
     */
    public String receive() {
        input = scann.nextLine();
        log(input);
        return input;
    }

    /**
     * Method to answer the GUI via std.out
     * @param output String that is send to GUI
     */
    public void answer(String output){
        log(output);
        System.out.println(output);
    }

    /**
     * Method to log messages
     * @param input message
     */
    public void log(String input){
        try {
            writer.write(input);
        }catch (IOException e){
            //TODO FEHLERMELDUNG LOG schreiben fehlgeschlagen
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
