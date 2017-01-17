package com.springernature;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

public class CombinedCommandsTest extends TextCanvasTest {

    @Test(expected = CommandParseException.class)
    public void testUnknownCommand() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("X 10 5").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testBogusCommand() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("WHATSTHAT").runCommand(state);
    }

    @Test(expected = PaintException.class)
    public void noCanvasException() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("L 1 1 1 1").runCommand(state);
    }

    @Test
    public void testMany() throws Exception {
        String resultMany = getFile("combinedTest.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 20 4").runCommand(state);
        Command.createCommandFromText("L 1 2 6 2").runCommand(state);
        Command.createCommandFromText("L 6 3 6 4").runCommand(state);
        Command.createCommandFromText("R 16 1 20 3").runCommand(state);
        Command.createCommandFromText("B 10 3 o").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(resultMany, resultRender);
    }

    @Test
    public void testStress() throws Exception {
        String resultMany = getFile("stressTest.txt");
        TextCanvasState state = new TextCanvasState();

        // maximum size canvas
        Command.createCommandFromText("C 50 50").runCommand(state);
        // maximum size rectangle
        Command.createCommandFromText("R 1 1 50 50").runCommand(state);

        // maximum size line
        Command.createCommandFromText("L 1 2 50 2").runCommand(state);
        // line with empty border left right
        Command.createCommandFromText("L 3 4 48 4").runCommand(state);
        // fill line
        Command.createCommandFromText("B 7 4 ?").runCommand(state);
        // fill surrounding
        Command.createCommandFromText("B 7 5 $").runCommand(state);

        // smaller rectangle
        Command.createCommandFromText("R 3 10 48 48").runCommand(state);

        // two lines letting a diagonal gap
        Command.createCommandFromText("L 4 12 6 12").runCommand(state);
        Command.createCommandFromText("L 7 11 7 11").runCommand(state);
        // make fill smaller rectangle, make sure the diagonal gap isnt leaking
        Command.createCommandFromText("B 7 13 *").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(resultMany, resultRender);
    }
}
