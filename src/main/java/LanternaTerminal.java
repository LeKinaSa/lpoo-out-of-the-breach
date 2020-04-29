import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.SimpleTerminalResizeListener;

import java.io.IOException;

// Code modified from https://github.com/arestivo/hero/blob/master/src/main/java/com/aor/hero/gui/Gui.java

public class LanternaTerminal {
    private final TerminalScreen screen;
    private final SimpleTerminalResizeListener termListener;

    public LanternaTerminal(int x, int y) throws IOException {
        TerminalSize terminalSize = new TerminalSize(x, y);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen();           // screens must be started
        screen.doResizeIfNecessary();   // resize screen if necessary

        termListener = new SimpleTerminalResizeListener(terminalSize);
        terminal.addResizeListener(termListener);
    }

    public void clear() {
        screen.clear();
    }

    public void refresh() throws IOException {
        screen.refresh();
    }

    public TextGraphics getTerminalBuffer() {
        return screen.newTextGraphics();
    }

    public KeyStroke pollInput() throws IOException {
        return screen.pollInput();
    }

    public void resizeIfNecessary() {
        screen.doResizeIfNecessary();
    }
}