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
 * This brush draws a rectangle on the canvas.
 */
public class Rectangle implements Brush {

    private Vector edge1;
    private Vector edge2;
    private char brushColor;

    public Rectangle(Vector edge1, Vector edge2, char brushChar) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.brushColor = brushChar;
    }
    @Override
    public void paint(Canvas canvas) throws PaintException {
        if (canvas == null) {
            throw new PaintException("No Canvas to paint on.");
        }

        // Calculating additional vectors
        Vector edge3 = new Vector(this.edge1.getX(), this.edge2.getY());
        Vector edge4 = new Vector(this.edge2.getX(), this.edge1.getY());

        // mimicking a true rectangle brush with 4 line brushes
        Line line1 = new Line(this.edge1, edge3, brushColor);
        Line line2 = new Line(this.edge1, edge4, brushColor);
        Line line3 = new Line(this.edge2, edge3, brushColor);
        Line line4 = new Line(this.edge2, edge4, brushColor);

        // Just paint those lines. there will be minor overpainting happen. Should be okay for now.
        line1.paint(canvas);
        line2.paint(canvas);
        line3.paint(canvas);
        line4.paint(canvas);
    }
}
