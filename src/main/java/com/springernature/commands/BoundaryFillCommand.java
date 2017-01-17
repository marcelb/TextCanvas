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

package com.springernature.commands;

import com.springernature.TextCanvasState;
import com.springernature.brushes.BoundaryFill;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;
import com.springernature.models.Vector;

/**
 * A command that uses a BoundaryFill brush to paint on a canvas.
 */
public class BoundaryFillCommand extends Command {

    private Vector vector;
    private char color;

    public BoundaryFillCommand(String[] splittedCommand) throws CommandParseException {
        super();

        if (splittedCommand.length != 4) {
            throw new CommandParseException("Wrong amount of parameters. B [X] [Y] [Color]");
        }

        try {
            int x = Integer.parseInt(splittedCommand[1]);
            int y = Integer.parseInt(splittedCommand[2]);
            this.color = splittedCommand[3].charAt(0);

            if (x < 1 || y < 1) {
                throw new CommandParseException("Parameters can't be 0 or less.");
            }

            this.vector = new Vector(x, y);
        } catch (Exception e) {
            throw new CommandParseException("Couldn't parse parameters. "+e.getMessage());
        }
    }

    @Override
    public void runCommand(TextCanvasState canvas) throws PaintException {
        BoundaryFill boundaryFill = new BoundaryFill(this.vector, this.color);
        boundaryFill.paint(canvas.getCanvas());
    }

    public Vector getVector() {
        return vector;
    }

    public char getColor() {
        return color;
    }
}
