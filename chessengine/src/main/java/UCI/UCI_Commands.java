package UCI;

/**
 *
 */
final class UCI_Commands {

    /**
     *  Private constructor so that this class may never be instanced
     *  If the constructor is somehow called throw error
     */
    private UCI_Commands() throws AssertionError {
        throw new AssertionError();
    }

    // Commands send from GUI to engine

    /**
     * First command sent to enable uci mode and start communication
     * Expects the engine to identify itself
     */
    static final String UCI = "uci";

    /**
     * check status of the engine, has to respond with READY_OK at all times
     */
    static final String IS_READY = "isready";

    /**
     * setoption + name + id + value to set options offered by the engine
     */
    static final String SET_OPTION = "setoption";

    /**
     * enable/disable debug mode -> information flagged with INFO will or will not be send
     */
    static final String DEBUG_MODE = "debug";

    /**
     * Some GUI's send this to tell the engine that a new game has been started
     *  -> Engine should not depend on it
     */
    static final String NEW_GAME = "ucinewgame";

    /**
     * Here the GUI sends the current board to the engine using a Fenstring
     */
    static final String POSITION = "position";

    /**
     * GUI tells engine tht the board has the standard layout
     */
    static final String STARTPOS = "startpos";

    /**
     * GUI sends the current boardlayout as a fenstring
     */
    static final String FEN = "fen";

    /**
     * GUI sends the moves the player has made
     */
    static final String MOVES = "moves";

    /**
     * Command to start the move generator and find the best move for the current board
     */
    static final String GO = "go";

    /**
     * Engine stops calculation as soon as possible, then sends the best move found by then
     */
    static final String STOP = "stop";

    /**
     * Stop calculating and terminate the engine immediately
     */
    static final String QUIT = "quit";

    // Commands send from engine to GUI

    /**
     * id + name is send to register the engine after receiving UCI
     */
    static final String ID = "id name ";

    /**
     * author + author name is send to register the engine after receiving UCI
     */
    static final String AUTHOR = "id author ";

    /**
     * after registering and offering of all options
     * the engine sends this to show it is ready for configuration
     */
    static final String UCI_OK = "uciok";

    /**
     * Answer to IS_READY, must be send when receiving the command as soon as possible
     * even when calculating
     */
    static final String READY_OK = "readyok";

    /**
     * Sends the best move found for the current board to the GUI
     */
    static final String BEST_MOVE = "bestmove ";

    /**
     * Offers all the configurable options to the GUI
     */
    static final String OPTION = "option name ";

    /**
     * Debug information must be flagged with info
     */
    static final String INFO = "info";

    /**
     * Type specifies the type of option offered, e.g. a button, checkbox, ...
     */
    static final String TYPE = "type";

}
