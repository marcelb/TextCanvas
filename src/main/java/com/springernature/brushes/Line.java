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
 * This brush draws a horizontal or vertical line on the canvas.
 */
public class Line implements Brush {

    private Vector start;
    private Vector end;
    private char brushColor;

    public Line(Vector start, Vector end, char brushChar) {
        this.start = start;
        this.end = end;
        this.brushColor = brushChar;
    }

    @Override
    public void paint(Canvas canvas) throws PaintException {
        if (canvas == null) {
            throw new PaintException("No Canvas to paint on.");
        }

        int xDelta = end.getX() - start.getX();
        int yDelta = end.getY() - start.getY();

        if (xDelta != 0) {
            if(xDelta>0) {
                for (int i = this.start.getX(); i <= this.end.getX(); i++) {
                    canvas.setPixel(i, this.start.getY(), this.brushColor);
                }
            } else {
                for (int i = this.start.getX(); i >= this.end.getX(); i--) {
                    canvas.setPixel(i, this.start.getY(), this.brushColor);
                }
            }
        } else {
            if(yDelta>0) {
                for (int i = this.start.getY(); i <= this.end.getY(); i++) {
                    canvas.setPixel(this.start.getX(), i, this.brushColor);
                }
            } else {
                for (int i = this.start.getY(); i >= this.end.getY(); i--) {
                    canvas.setPixel(this.start.getX(), i, this.brushColor);
                }
            }
        }
    }
}
