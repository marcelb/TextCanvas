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
import com.springernature.brushes.Line;
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;
import com.springernature.models.Vector;

/**
 * A command that uses a Line brush to paint on a canvas.
 */
public class LineCommand extends Command {

    public static final char BRUSH_CHAR = 'x';

    private Vector start;
    private Vector end;

    public LineCommand(String[] splittedCommand) throws CommandParseException {
        super();

        if (splittedCommand.length != 5) {
            throw new CommandParseException("Wrong amount of parameters. L [startX] [startY] [endX] [endY]");
        }

        try {
            int startx = Integer.parseInt(splittedCommand[1]);
            int starty = Integer.parseInt(splittedCommand[2]);
            int endx = Integer.parseInt(splittedCommand[3]);
            int endy = Integer.parseInt(splittedCommand[4]);

            if (startx < 1 || starty < 1 || endx < 1 || endy < 1) {
                throw new CommandParseException("Parameters can't be 0 or less.");
            }

            if (startx != endx && starty != endy) {
                throw new CommandParseException("No diagonal lines supported yet.");
            }

            this.start = new Vector(startx, starty);
            this.end = new Vector(endx, endy);

        } catch (Exception e) {
            throw new CommandParseException("Couldn't parse parameters. "+e.getMessage());
        }
    }

    @Override
    public void runCommand(TextCanvasState canvas) throws PaintException {
        Line line = new Line(this.start, this.end, BRUSH_CHAR);
        line.paint(canvas.getCanvas());
    }

    public Vector getStart() {
        return start;
    }

    public Vector getEnd() {
        return end;
    }
}
