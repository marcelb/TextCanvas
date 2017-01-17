package com.springernature;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

public class CanvasCommandTest extends TextCanvasTest {

    @Test(expected = CommandParseException.class)
    public void testNegativeArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C -1 5").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testFewArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNonNumericArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C A 5").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testNoArguments() throws Exception {
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C").runCommand(state);
    }

    @Test(expected = CommandParseException.class)
    public void testOverlayLargeCanvas() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("C 51 5").runCommand(state);
    }

    @Test
    public void testOnePixel() throws Exception {
        String result = getFile("onePixelEmpty.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 1 1").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }

    @Test(expected = CommandParseException.class)
    public void testZeroPixel() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("C 0 0").runCommand(state);
    }


    @Test
    public void test10x5() throws Exception {
        String result = getFile("emptyCanvas10x5.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 10 5").runCommand(state);

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
    public void testRepeatable() throws Exception {
        String result = getFile("emptyCanvas10x5.txt");
        TextCanvasState state = new TextCanvasState();

        Command.createCommandFromText("C 30 30").runCommand(state);
        Command.createCommandFromText("C 10 5").runCommand(state);

        String resultRender = state.getCanvas().renderAsString();
        Assert.assertEquals(result, resultRender);
    }
}
