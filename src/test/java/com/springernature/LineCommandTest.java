package com.springernature;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

public class LineCommandTest extends TextCanvasTest {

    @Test(expected = CommandParseException.class)
    public void testNegativeArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L -1 5 10 5").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testFewArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L 1 5 10").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNonNumericArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L 1 5 10 A").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNoArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L").runCommand(state);
    }

    @Test
    public void testOneLine() throws Exception {
        String result = getFile("lineResult1.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L 1 5 10 5").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test
    public void testOneReverse() throws Exception {
        String result = getFile("lineResult1.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L 10 5 1 5").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test
    public void testOnePixel() throws Exception {
        String result = getFile("onePixel.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 3 3").runCommand(state);
        Command.createCommandFromText("L 2 2 2 2").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test(expected=PaintException.class)
    public void testOutOfCanvas() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L 1 5 20 5").runCommand(state);
    }

    @Test
    public void testMany() throws Exception {
        String resultMany = getFile("lineResultMany.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("L 1 5 10 5").runCommand(state);
        Command.createCommandFromText("L 5 1 5 5").runCommand(state);
        Command.createCommandFromText("L 6 5 6 1").runCommand(state);
        Command.createCommandFromText("L 4 2 7 2").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(resultMany, resultRender);
    }
}
