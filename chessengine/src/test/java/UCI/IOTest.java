package UCI;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {
    String command;
    IO io;

    @BeforeEach
    void setUp() {
        command = "Command Test Test Test";
        io = new IO();
    }

    @Test
    void commandCutter() {
        Assert.assertEquals(io.commandCutter(command),"Command");
    }
}