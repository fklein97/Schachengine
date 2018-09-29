package UCI;

import Parameters.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class UCI that interprets given Strings as UCI commands and launches related methods
 */
public class UCI {

    private IO io;
    private OperationManager manager;
    private boolean masterExit;
    private boolean readyFEN;
    private Thread goThread;

    /**
     * Constructor to set up I /O
     */
    public UCI(){
        readyFEN = true;
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
                Thread.sleep(1000);
                input = io.receive();
                if (!(input.isEmpty())) {
                    commandDetection(input, io.commandCutter(input));
                }
            }
        }catch(Throwable error){
            error.printStackTrace();
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
        io.answer(UCI_Commands.ID + "Chessica");
        io.answer(UCI_Commands.AUTHOR + "htw saar");

        offerOptions();

        io.answer(UCI_Commands.UCI_OK);
    }

    /**
     * Method to send all possible options to the GUI
     */
    public void offerOptions(){
        io.answer(UCI_Commands.OPTION + "Depth type spin default " + Parameters.Depth + " min 2 max 10");
        io.answer(UCI_Commands.OPTION + "use_AlphaBeta type check default " + Boolean.toString(Parameters.useAlphaBeta));
        io.answer(UCI_Commands.OPTION + "RandomizerValue type spin default " + Parameters.randomizerValue + " min 0 max 100");
        io.answer(UCI_Commands.OPTION + "use_Material_Rating type check default " + Boolean.toString(Parameters.useMaterialRating));
        io.answer(UCI_Commands.OPTION + "use_KinginDanger_Rating type check default " + Boolean.toString(Parameters.useKinginDangerRating));
        io.answer(UCI_Commands.OPTION + "KinginDanger_PieceCount type spin default " + Parameters.KinginDangerPieceCount + " min 1 max 50");
        io.answer(UCI_Commands.OPTION + "use_DangerPositions_Rating type check default " + Boolean.toString(Parameters.useDangerPositionsRating));
        io.answer(UCI_Commands.OPTION + "use_Position_Rating type check default " + Boolean.toString(Parameters.usePositionRating));
        io.answer(UCI_Commands.OPTION + "use_PawnStructure_Rating type check default " + Boolean.toString(Parameters.usePawnStructureRating));
    }

    /**
     * GUI will send this to check the engine status, always answer with "readyok"
     */
    public void isReady(){
        io.answer(UCI_Commands.READY_OK);
    }

    /**
     * receives the parameters for the offered options and sets them
     * @param input info string by the GUI to set the options
     */
    public void setOption(String input){
        String[] input_array = input.split(" ");
        if(input_array[2].equals("Depth")){
            Parameters.Depth = Integer.parseInt(input_array[4]);
            IO.sendDebugInfo("Engine Depth has been set!");
        }
        else if (input_array[2].equals("use_AlphaBeta")){
            if(input_array[4].equals("true")){
                Parameters.useAlphaBeta = true;
                IO.sendDebugInfo("The Engine now uses AlphaBeta!");
            }
            else if(input_array[4].equals("false")){
                Parameters.useAlphaBeta = false;
                IO.sendDebugInfo("The Engine now doesnt use AlphaBeta!");
            }
        }
        else if(input_array[2].equals("RandomizerValue")){
            Parameters.randomizerValue = Integer.parseInt(input_array[4]);
            IO.sendDebugInfo("The Randomizer Value has been set!");
        }
        else if (input_array[2].equals("use_Material_Rating")) {
            if (input_array[4].equals("true")) {
                Parameters.useMaterialRating = true;
                IO.sendDebugInfo("The Engine now uses MaterialRating!");
            } else if (input_array[4].equals("false")) {
                Parameters.useMaterialRating = false;
                IO.sendDebugInfo("The Engine now doesnt use MaterialRating!");
            }
        }
        else if (input_array[2].equals("use_KinginDanger_Rating")) {
            if (input_array[4].equals("true")) {
                Parameters.useKinginDangerRating = true;
                IO.sendDebugInfo("The Engine now uses KinginDangerRating!");
            } else if (input_array[4].equals("false")) {
                Parameters.useKinginDangerRating = false;
                IO.sendDebugInfo("The Engine now doesnt use KinginDangerRating!");
            }
        }
        else if(input_array[2].equals("KinginDanger_PieceCount")){
            Parameters.KinginDangerPieceCount = Integer.parseInt(input_array[4]);
            IO.sendDebugInfo("The KinginDanger Piececount has been set!");
        }
        else if (input_array[2].equals("use_DangerPositions_Rating")) {
            if (input_array[4].equals("true")) {
                Parameters.useDangerPositionsRating = true;
                IO.sendDebugInfo("The Engine now uses DangerPositionsRating!");
            } else if (input_array[4].equals("false")) {
                Parameters.useDangerPositionsRating = false;
                IO.sendDebugInfo("The Engine now doesnt use DangerPositionsRating!");
            }
        }
        else if (input_array[2].equals("use_Position_Rating")) {
            if (input_array[4].equals("true")) {
                Parameters.usePositionRating = true;
                IO.sendDebugInfo("The Engine now uses PositionRating!");
            } else if (input_array[4].equals("false")) {
                Parameters.usePositionRating = false;
                IO.sendDebugInfo("The Engine now doesnt use PositionRating!");
            }
        }
        else if (input_array[2].equals("use_PawnStructure_Rating")) {
            if (input_array[4].equals("true")) {
                Parameters.usePawnStructureRating = true;
                IO.sendDebugInfo("The Engine now uses PawnStructureRating!");
            } else if (input_array[4].equals("false")) {
                Parameters.usePawnStructureRating = false;
                IO.sendDebugInfo("The Engine now doesnt use PawnStructureRating!");
            }
        }
    }

    /**
     * Toggle to enable/disable "info" messages
     */
    public void debug() {
        if (Parameters.debugMode == false) {
            Parameters.debugMode = true;
            IO.sendDebugInfo("Debug Mode now activ");
        } else {
            Parameters.debugMode = false;
        }
    }

    /**
     * Some GUI's send this to signal that a new game has started
     */

    public void ucinewgame(){
        manager = new OperationManager();
        Parameters.isColorSet = false;
        readyFEN = true;
    }

    /**
     * This method receives the board from the GUI and handles player movement
     * @param input board and moving infos
     */
    public void position(String input){

        IO.sendDebugInfo("Start moving");

        input = input.substring(9);
        if (input.contains(UCI_Commands.STARTPOS)){

        } else if ((input.contains(UCI_Commands.FEN) && (readyFEN == true))) {
            input = input.substring(4);
            manager.handleFEN(input);
            readyFEN = false;
        }
        if (input.contains(UCI_Commands.MOVES)){
            input = input.substring(input.length()-5).trim();
            manager.playerMove(input);
            if(Parameters.isColorSet == false) {
                Parameters.isEngineWhite = false;
                IO.sendDebugInfo("IM BLACK!");
                Parameters.isColorSet = true;
            }
        }
        else{
            if(Parameters.isColorSet == false) {
                Parameters.isEngineWhite = true;
                IO.sendDebugInfo("IM WHITE!");
                Parameters.isColorSet = true;
            }
        }
        IO.sendDebugInfo("Move succesful");
    }

    /**
     * Starts the calculation of the best possible move
     * @param input infos
     */
    public void go(String input){

        /**
         * Set up time management
         */
        ArrayList<String> times = new ArrayList<>();
        if (input.contains("wtime") && input.contains("btime")) {
            String temp = input;
            temp = temp.replaceAll("[^0-9]+", " ");
            times.addAll(Arrays.asList(temp.trim().split(" ")));
            if (Parameters.isEngineWhite) {
                manager.getTimer().setEngineTime(Integer.parseInt(times.get(0)));
                manager.getTimer().setPlayerTime(Integer.parseInt(times.get(1)));
            } else {
                manager.getTimer().setEngineTime(Integer.parseInt(times.get(1)));
                manager.getTimer().setPlayerTime(Integer.parseInt(times.get(0)));
            }
        }

        IO.sendDebugInfo("start go");
        goThread = new Thread() {
            @Override
            public void run(){
                String response = manager.go(input);
                io.answer(UCI_Commands.BEST_MOVE + response);
                IO.sendDebugInfo("Go succesful");
            }
        };
        goThread.setDaemon(true);
        goThread.start();
    }

    /**
     * Stop the calculation as soon as possible and send the best possible move
     */
    public void stop(){
        if (goThread.isAlive()) {
            goThread.interrupt();
        }
    }

    /**
     * set the masterExit to false to close the input loop
     */
    public void quit(){
        masterExit = false;
    }

}
