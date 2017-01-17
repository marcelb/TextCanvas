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

package com.springernature.brushes;

import com.springernature.Canvas;
import com.springernature.exceptions.PaintException;
import com.springernature.models.Vector;

/**
 * This brush boundary fills an area.
 */
public class BoundaryFill implements Brush {

    private Vector vector;
    private char brushColor;


    public BoundaryFill(Vector vector, char brushChar) {
        this.vector = vector;
        this.brushColor = brushChar;
    }

    @Override
    public void paint(Canvas canvas) throws PaintException {
        if (!canvas.isOnCanvas(this.vector)) {
            throw new PaintException("Trying to boundary fill outside canvas.");
        }

        // we don't need to flood fill, if the target pixel it is already colored brushColor
        if (canvas.getPixel(vector) != brushColor) {
            floodFill(vector, canvas.getPixel(vector), brushColor, canvas);
        }
    }

    /**
     * This is a highly recursive approach that is easy to understand and write, but could pose a stack overflow problem
     * on really big canvas.
     *
     * @param vector
     * @param oldColor
     * @param newColor
     * @param canvas
     * @throws PaintException
     */
    private void floodFill(Vector vector, char oldColor, char newColor, Canvas canvas) throws PaintException {
        try {
            if (canvas.getPixel(vector) == oldColor) {
                canvas.setPixel(vector, newColor);
                floodFill(new Vector(vector.getX(), vector.getY() + 1), oldColor, newColor, canvas);
                floodFill(new Vector(vector.getX(), vector.getY() - 1), oldColor, newColor, canvas);
                floodFill(new Vector(vector.getX() - 1, vector.getY()), oldColor, newColor, canvas);
                floodFill(new Vector(vector.getX() + 1, vector.getY()), oldColor, newColor, canvas);
            }
        } catch (PaintException e) {
        }
    }
}
