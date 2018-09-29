package Board;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataManagerTest {
    DataManager manager;

    @BeforeEach
    void setUp() {
        manager = new DataManager();
    }

    @AfterEach
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