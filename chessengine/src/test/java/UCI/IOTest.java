package UCI;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class IOTest {
    String command;
    IO io;

    @Before
    public void setUp() {
        command = "Command Test Test Test";
        io = new IO();
    }

    @Test
    public void commandCutter() {
        Assert.assertEquals(io.commandCutter(command),"Command");
    }


}