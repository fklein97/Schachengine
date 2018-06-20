/**
 * Class UCI that interprets given Strings as UCI commands and launches related methods
 */
public class UCI {

    private IO io;
    private OperationManager manager;
    private boolean masterExit;

    /**
     * Constructor to set up I /O
     */
    public UCI(){
        masterExit = true;
        io = new IO();
        manager = new OperationManager();
    }

    /**
     * Method to get inputs from I/O
     */
    public void getCommand(){
        String input;
        try {
            while (masterExit) {
                input = io.receive();
                if (!(input.isEmpty())) {
                    commandDetection(input, io.commandCutter(input));
                }
            }
        }catch(Throwable error){

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
     * Method to register the Engine
     */
    public void uci(){
        io.answer(UCI_Commands.ID /*TODO + NAMENSKONSTANTE*/);
        io.answer(UCI_Commands.AUTHOR /*TODO + AUTORENKONSTANTE*/);

        offerOptions();

        io.answer(UCI_Commands.UCI_OK);
    }

    /**
     * Method to send all possible options to the GUI
     */
    public void offerOptions(){
        io.answer(UCI_Commands.OPTION);
    }

    /**
     * GUI will send this to check the engine status, always answer with "readyok"
     */
    public void isReady(){
        io.answer(UCI_Commands.READY_OK);
    }

    /**
     * receives the parameters for the offered options and sets them
     */
    public void setOption(String input){
        //TODO
    }

    /**
     * Toggle to enable/disable "info" messages
     */
    public void debug() {

        if (manager.isDebug() == false) {
            manager.setDebug(true);
        } else {
            manager.setDebug(false);
        }
    }

    /**
     * Some GUI's send this to signal that a new game has started
     */

    public void ucinewgame(){
        // nothing to do here
    }

    /**
     * This method receives the board from the GUI
     */
    public void position(String input){

        input = input.substring(9);
        if (input.contains(UCI_Commands.STARTPOS)){

        } else if (input.contains(UCI_Commands.FEN)){
            input = input.substring(4);
            manager.handleFEN(input);

        }

        if (input.contains(UCI_Commands.MOVES)){
            input = input.substring(input.length()-5).trim();
            io.answer(input);
            manager.playerMove(input);

        }
    }

    /**
     * Starts the calculation of the best possible move
     */
    public void go(String input){

        io.answer(manager.go(input.substring(3)));

    }

    /**
     * Stop the calculation as soon as possible and send the best possible move
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
