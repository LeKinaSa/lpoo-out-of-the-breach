import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

// Code modified from https://github.com/arestivo/hero/blob/master/src/main/java/com/aor/hero/gui/Gui.java

public class Gui {
    private final TerminalScreen screen;

    public Gui(int x, int y) throws IOException {
        TerminalSize terminalSize = new TerminalSize(x, y);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void draw() throws IOException {
        screen.clear();
        // Draw components
        screen.refresh();
    }
}