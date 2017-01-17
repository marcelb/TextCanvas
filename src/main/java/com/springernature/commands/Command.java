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
import com.springernature.exceptions.CommandParseException;
import com.springernature.exceptions.PaintException;

/**
 * Command interface.
 */
public abstract class Command {

    public static final String DELIMITER = " ";

    /**
     * Runs the command on a TextCanvasState
     * @param canvas
     * @throws PaintException
     */
    public abstract void runCommand(TextCanvasState canvas) throws PaintException;

    /**
     * Parses a string and returns the command that was found or throws an Exception if not possible.
     *
     * @param text
     * @return
     * @throws CommandParseException
     */
    public static Command createCommandFromText(String text) throws CommandParseException {
        if (text == null ) {
            throw new CommandParseException(getCommandHelp());
        }

        String trimmedCommand = text.trim();
        if (trimmedCommand.isEmpty()) {
            throw new CommandParseException(getCommandHelp());
        }

        String[] splittedCommand = trimmedCommand.split(DELIMITER);
        String command = splittedCommand[0].toUpperCase();

        try {
            // Example: C 20 4
            if ("C".equals(command)) {
                return new CanvasCommand(splittedCommand);
            }
            // Example: L 1 2 6 2
            if ("L".equals(command)) {
                return new LineCommand(splittedCommand);
            }
            // Example: R 16 1 20 3
            if ("R".equals(command)) {
                return new RectangleCommand(splittedCommand);
            }
            // Example: B 10 3 o
            if ("B".equals(command)) {
                return new BoundaryFillCommand(splittedCommand);
            }
            // Example: Q
            if ("Q".equals(command)) {
                return new QuitCommand();
            }
        } catch (CommandParseException e) { // just proxy
            throw e;
        } catch (Exception e) { // converting unexpected exceptions to CommandParseExceptions
            throw new CommandParseException(e.getMessage());
        }

        throw new CommandParseException(getCommandHelp());
    }

    /**
     * Command Help.
     *
     * @return
     */
    private static String getCommandHelp() {
        return "Command couldn't be parsed. \n" +
                "Available commands are: \n" +
                "New Canvas: C width height \n" +
                "Draw Line: L x1 y2 x2 y2 \n" +
                "Draw rectangle: R x1 y2 x2 y2 \n" +
                "Boundary Fill: B x y \n" +
                "Quit: Q \n";
    }
}
