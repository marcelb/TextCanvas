package com.springernature;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

public class RectangleCommandTest extends TextCanvasTest {

    @Test(expected = CommandParseException.class)
    public void testNegativeArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R -1 5 10 5").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testFewArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R 1 5 10").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNonNumericArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R 1 5 10 A").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNoArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R").runCommand(state);
    }

    @Test
    public void testOneRectangle() throws Exception {
        String result = getFile("simpleRectangle.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R 1 1 10 5").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test
    public void testOneRectangleReverse() throws Exception {
        String result = getFile("simpleRectangle.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R 10 5 1 1").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test
    public void testOnePixel() throws Exception {
        String result = getFile("onePixel.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 3 3").runCommand(state);
        Command.createCommandFromText("R 2 2 2 2").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test(expected=PaintException.class)
    public void testOutOfCanvas() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R 1 1 20 5").runCommand(state);
    }

    @Test
    public void testMany() throws Exception {
        String resultMany = getFile("xFilled.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("R 1 1 10 5").runCommand(state);
        Command.createCommandFromText("R 2 2 9 4").runCommand(state);
        Command.createCommandFromText("R 3 3 8 3").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(resultMany, resultRender);
    }
}
