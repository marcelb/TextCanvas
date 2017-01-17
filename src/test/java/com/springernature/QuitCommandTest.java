package com.springernature;

import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;

public class QuitCommandTest extends TextCanvasTest {

    @Test
    public void testQuit() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Command.createCommandFromText("Q").runCommand(state);
        Assert.assertEquals(state.isQuit(), true);
    }

    @Test
    public void testNonQuit() throws Exception {
        TextCanvasState state = new TextCanvasState();
        Assert.assertEquals(state.isQuit(), false);
    }


}
