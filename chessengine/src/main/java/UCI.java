
/**
 * Class UCI that interprets given Strings as UCI commands and launches related methods
 */
public class UCI {

    private IO io;
    private boolean masterExit;

    /**
     * Constructor to set up I/O
     */
    public UCI(){
        masterExit = true;
        io = new IO();
    }

    /**
     *
     */
    public void getCommand(){
        String input;

        while(masterExit){
            input = io.receive();
            if (!(input.isEmpty())) {
                commandDetection(input, io.commandCutter(input));
            }
        }
    }

    /**
     * Method to detect received commands from the GUI
     * @param input = Input read from System.in
     */
    public void commandDetection(String input, String command){

        switch(command){

            case UCI_Commands.UCI:  uci(); break;

            case UCI_Commands.IS_READY: isReady(); break;

            case UCI_Commands.SET_OPTION: setOption(input); break;

            case UCI_Commands.DEBUG_MODE: debug(); break;

            case UCI_Commands.NEW_GAME: ucinewgame(); break;

            case UCI_Commands.POSITION: position(input); break;

            case UCI_Commands.GO: go(input); break;

            case UCI_Commands.STOP: stop(); break;

            case UCI_Commands.QUIT: quit(); break;

            default: break;
        }
    }


    /**
     *
     */
    public void uci(){
        io.answer(UCI_Commands.ID);
        io.answer(UCI_Commands.AUTHOR);

        offerOptions();

        io.answer(UCI_Commands.UCI_OK);
    }

    /**
     *
     */
    public void offerOptions(){
        io.answer(UCI_Commands.OPTION);
    }

    /**
     *
     */
    public void isReady(){
        io.answer(UCI_Commands.READY_OK);
    }

    /**
     *
     */
    public void setOption(String input){
        //TODO
    }

    /**
     *
     */
    public void debug(){
        //TODO
    }

    /**
     *
     */
    public void ucinewgame(){
        // nothing to do here
    }

    /**
     *
     */
    public void position(String input){
        // First cut off the "position" from the input
        input = input.substring(9);
        if (input.contains(UCI_Commands.STARTPOS)){
            //TODO Standardboardgenerierung

        } else if (input.contains(UCI_Commands.FEN)){
            input = input.substring(4);
            //TODO Sende Fenstring an Boardgenerierung

        }

        if (input.contains(UCI_Commands.MOVES)){
            input = input.substring(input.length()-5).trim();
            io.answer(input);
            //TODO Sende Zug an Bordgenerierung
        }
    }

    /**
     *
     */
    public void go(String input){
        //TODO Start the calculation
    }

    /**
     *
     */
    public void stop(){
        //TODO Print best move found
    }

    /**
     * set the masterExit to false to close the input loop
     */
    public void quit(){
        masterExit = false;
    }

}
