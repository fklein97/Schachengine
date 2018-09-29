package Board;

import org.junit.Assert;
import org.junit.Assert;
import org.junit.*;


class DataManagerTest {
    DataManager manager;

    @Before
    void setUp() {
        manager = new DataManager();
    }

    @After
    void tearDown() {
        manager.disbandHistory();
    }

    @Test
    void getLast() {
        ChessBoard board = new ChessBoard();
        manager.addBoard(board);
        Assert.assertEquals(board,manager.getLast());
    }

    @Test
    void addBoard() {
        ChessBoard board = new ChessBoard();
        manager.addBoard(board);
        manager.getHistory().contains(board);
    }
}