package com.springernature.commands;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.TextCanvasTest;
import com.springernature.exceptions.CommandParseException;
import com.springernature.models.Vector;

public class CommandsTest extends TextCanvasTest {

    @Test
    public void testCanvasCommandSuccess() throws Exception {
        Command commandLowerCase = Command.createCommandFromText("c 10 10");
        Assert.assertTrue(commandLowerCase instanceof CanvasCommand);

        Command command = Command.createCommandFromText("C 10 10");
        Assert.assertTrue(command instanceof CanvasCommand);

        CanvasCommand canvasCommand = (CanvasCommand) command;

        Assert.assertEquals(new Vector(10, 10), canvasCommand.getVector());
    }

    @Test
    public void testLineCommandSuccess() throws Exception {
        Command commandLowerCase = Command.createCommandFromText("l 1 1 10 1");
        Assert.assertTrue(commandLowerCase instanceof LineCommand);

        Command command = Command.createCommandFromText("L 1 1 10 1");
        Assert.assertTrue(command instanceof LineCommand);

        LineCommand lineCommand = (LineCommand) command;

        Assert.assertEquals(new Vector(1, 1), lineCommand.getStart());
        Assert.assertEquals(new Vector(10, 1), lineCommand.getEnd());
    }

    @Test
    public void testRectangleCommandSuccess() throws Exception {
        Command commandLowerCase = Command.createCommandFromText("r 1 1 10 1");
        Assert.assertTrue(commandLowerCase instanceof RectangleCommand);

        Command command = Command.createCommandFromText("R 1 1 10 1");
        Assert.assertTrue(command instanceof RectangleCommand);

        RectangleCommand rectangleCommand = (RectangleCommand) command;

        Assert.assertEquals(new Vector(1, 1), rectangleCommand.getStart());
        Assert.assertEquals(new Vector(10, 1), rectangleCommand.getEnd());
    }

    @Test
    public void testBoundaryFillCommandSuccess() throws Exception {
        Command commandLowerCase = Command.createCommandFromText("b 10 10 q");
        Assert.assertTrue(commandLowerCase instanceof BoundaryFillCommand);

        Command command = Command.createCommandFromText("B 10 10 q");
        Assert.assertTrue(command instanceof BoundaryFillCommand);

        BoundaryFillCommand boundaryFillCommand = (BoundaryFillCommand) command;

        Assert.assertEquals(new Vector(10, 10), boundaryFillCommand.getVector());
        Assert.assertEquals('q', boundaryFillCommand.getColor());
    }

    @Test
    public void testQuitCommandSuccess() throws Exception {
        Command command1 = Command.createCommandFromText("Q");
        Assert.assertTrue(command1 instanceof QuitCommand);

        Command command2 = Command.createCommandFromText("q");
        Assert.assertTrue(command2 instanceof QuitCommand);
    }

    @Test(expected = CommandParseException.class)
    public void testEmptyCommand() throws Exception {
        Command.createCommandFromText("");
    }

    @Test(expected = CommandParseException.class)
    public void testNullCommand() throws Exception {
        Command.createCommandFromText(null);
    }

    @Test(expected = CommandParseException.class)
    public void testBogusCommand() throws Exception {
        Command.createCommandFromText("dhro3i2hewkdjs ssd sad 3ed");
    }
}
