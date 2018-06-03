import java.util.Scanner;

/**
 *  Class Input/Output managing all communication tasks
 */
public class IO {

    public final String BLANK = " ";
    public final String EMPTY = "";

    private String input;
    private String command;
    private Scanner scann;

    /**
     * Constructor to set up the scanner
     */
    public IO() {
        input = EMPTY;
        command = EMPTY;
        scann = new Scanner(System.in);
    }

    /**
     * Method to read a full line from System.in
     */
    public String receive() {
        //TODO WRITE TO LOG
        return input = scann.nextLine();
    }

    /**
     * Method to answer the GUI via std.out
     * @param output String that will be send to GUI
     */
    public void answer(String output){
        //TODO WRITE TO LOG
        System.out.println(output);
    }

    /**
     * @param input full line of input
     * @return output , just the command send by the GUI
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
