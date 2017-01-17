// Copyright Marcel Bankmann <marcel.bankmann@gmail.com>
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.springernature;

import com.springernature.exceptions.PaintException;
import com.springernature.models.Vector;

/**
 * A text canvas of variable size. This class supports low level manipulation of the canvas and output.
 */
public class Canvas {
    public static final char EMPTY_CHAR = ' ';
    public static final String HORIZONTAL_BORDER = "-";
    public static final String VERTICAL_BORDER = "|";
    char[][] canvasContent;
    int width = 0;
    int height = 0;

    public Canvas(Vector vector) {
        this.width = vector.getX();
        this.height = vector.getY();
        this.canvasContent = new char[height][width];
        clearCanvas();
    }

    /**
     * Render current canvas to stdout
     */
    public void render() {
        System.out.println(renderAsString());
    }

    /**
     * Render current canvas as a string
     * @return
     */
    public String renderAsString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<(this.width+2); i++) {
            stringBuilder.append(HORIZONTAL_BORDER);
        }

        stringBuilder.append("\n");

        for (int i=0; i<canvasContent.length; i++) {
            stringBuilder.append(VERTICAL_BORDER);
            stringBuilder.append(String.valueOf(canvasContent[i]));
            stringBuilder.append(VERTICAL_BORDER);
            stringBuilder.append("\n");
        }

        for (int i=0; i<(this.width+2); i++) {
            stringBuilder.append(HORIZONTAL_BORDER);
        }

        return stringBuilder.toString();
    }

    /**
     * Clear the current canvas.
     */
    public void clearCanvas() {
        if (this.canvasContent != null) {
            for (int y=0; y<canvasContent.length; y++) {
                for (int x=0; x<canvasContent[y].length; x++) {
                    canvasContent[y][x] = EMPTY_CHAR;
                }
            }
        }
    }

    /**
     * Set a pixel via a vector.
     *
     * @param vector
     * @param color
     * @throws PaintException
     */
    public void setPixel(Vector vector, char color) throws PaintException {
        this.setPixel(vector.getX(), vector.getY(), color);
    }

    /**
     * Set a pixel.
     *
     * @param x
     * @param y
     * @param color
     * @throws PaintException
     */
    public void setPixel(int x, int y, char color) throws PaintException {
        if (this.canvasContent == null) {
            throw new PaintException("Drawing without a canvas.");
        }

        if (x>width || y>height || x<1 || y<1) {
            throw new PaintException("Drawing outside the canvas.");
        }

        this.canvasContent[y-1][x-1] = color;
    }

    /**
     * Get the color of a pixel via vector.
     *
     * @param vector
     * @return
     * @throws PaintException
     */
    public char getPixel(Vector vector) throws PaintException {
        return this.getPixel(vector.getX(), vector.getY());
    }

    /**
     * Get the color of a pixel.
     *
     * @param x
     * @param y
     * @return
     * @throws PaintException
     */
    public char getPixel(int x, int y) throws PaintException {
        if (this.canvasContent == null) {
            throw new PaintException("Reading from none-existent canvas.");
        }

        if (x>width || y>height || x<1 || y<1) {
            throw new PaintException("Reading color outside the canvas.");
        }
        return this.canvasContent[y-1][x-1];
    }

    /**
     * Returns true if the vector is pointing to an existing part of the canvas.
     *
     * @param vector
     * @return
     * @throws PaintException
     */
    public boolean isOnCanvas(Vector vector) throws PaintException {
        return this.isOnCanvas(vector.getX(), vector.getY());
    }

    /**
     * Returns true if the given point is existing on the canvas.
     *
     * @param x
     * @param y
     * @return
     * @throws PaintException
     */
    public boolean isOnCanvas(int x, int y) throws PaintException {
        if (this.canvasContent == null) {
            throw new PaintException("Accessing none-existent canvas.");
        }

        if (x>width || y>height || x<1 || y<1) {
            return false;
        }

        return true;
    }
}
