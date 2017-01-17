package com.springernature;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

public class BoundaryFillCommandTest extends TextCanvasTest {

    @Test(expected = CommandParseException.class)
    public void testNegativeArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B -1 5").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testFewArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 1").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNonNumericArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 1 X").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNoArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B").runCommand(state);
    }

    @Test
    public void testXFill() throws Exception {
        String result = getFile("xFilled.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 1 1 x").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test
    public void testDoubleFill() throws Exception {
        String result = getFile("xFilled.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 1 1 x").runCommand(state);
        Command.createCommandFromText("B 1 1 x").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test
    public void testXFillReverse() throws Exception {
        String result = getFile("xFilled.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 10 5 x").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test(expected=PaintException.class)
    public void testOutOfCanvas() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 20 20 x").runCommand(state);
    }

    @Test
    public void testMany() throws Exception {
        String resultMany = getFile("oFilled.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);
        Command.createCommandFromText("B 1 1 x").runCommand(state);
        Command.createCommandFromText("B 5 5 x").runCommand(state);
        Command.createCommandFromText("B 5 5 o").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(resultMany, resultRender);
    }
}
