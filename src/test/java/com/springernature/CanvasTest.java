package com.springernature;

import com.springernature.models.Vector;
import org.junit.Assert;
import org.junit.Test;

import com.springernature.commands.Command;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

public class CanvasTest extends TextCanvasTest {

    @Test
    public void testCreateCanvasAndRenderAsString() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        Assert.assertEquals(155, canvas.renderAsString().length());
    }

    @Test
    public void testClearCanvas() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        Assert.assertEquals(' ', canvas.getPixel(5,5));
        canvas.setPixel(5, 5, '#');
        Assert.assertEquals('#', canvas.getPixel(5,5));
        canvas.clearCanvas();
        Assert.assertEquals(' ', canvas.getPixel(5,5));
    }

    @Test
    public void testSetGetPixel() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        canvas.setPixel(5, 5, '#');
        Assert.assertEquals('#', canvas.getPixel(5,5));
    }

    @Test
    public void testSetGetPixelVector() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        canvas.setPixel(new Vector(5, 5), '#');
        Assert.assertEquals('#', canvas.getPixel(new Vector(5,5)));
    }

    @Test(expected = PaintException.class)
    public void testSetPixelOutsideTooLow() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        canvas.setPixel(new Vector(-1, 5), '#');
    }

    @Test(expected = PaintException.class)
    public void testSetPixelOutsideTooHigh() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        canvas.setPixel(new Vector(1, 11), '#');
    }

    @Test(expected = PaintException.class)
    public void testGetPixelOutsideTooLow() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        canvas.getPixel(new Vector(-1, 5));
    }

    @Test(expected = PaintException.class)
    public void testGetPixelOutsideTooHigh() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        canvas.getPixel(new Vector(1, 11));
    }

    @Test
    public void testIsOnCanvas() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        Assert.assertEquals(true, canvas.isOnCanvas(new Vector(1,1)));
        Assert.assertEquals(true, canvas.isOnCanvas(new Vector(10,10)));
        Assert.assertEquals(true, canvas.isOnCanvas(new Vector(1,1)));
        Assert.assertEquals(true, canvas.isOnCanvas(new Vector(10,10)));
    }

    @Test
    public void testIsNotOnCanvasTooLow() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        Assert.assertEquals(false, canvas.isOnCanvas(new Vector(-1,1)));
    }

    @Test
    public void testIsNotOnCanvasTooHigh() throws Exception {
        Canvas canvas = new Canvas(new Vector(10,10));
        Assert.assertEquals(false, canvas.isOnCanvas(new Vector(10,11)));
    }
}
