package Parameters;

/**
 * Stores all engine parameters
 * @author Florian Klein
 */
public class Parameters {
    public static boolean isEngineWhite = false;
    public static boolean isColorSet = false;

    public static int Depth = 4;
    public static int randomizerValue = 0;

    public static boolean useAlphaBeta = true;

    public static boolean useMaterialRating = true;
    public static boolean useKinginDangerRating = true;
    public static int KinginDangerPieceCount = 15;
    public static boolean useDangerPositionsRating = true;
    public static boolean usePositionRating = true;
    public static boolean usePawnStructureRating = true;

    public static double turnTime = 0;
    public static boolean debugMode = false;
}
