package GUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIRoot {
    private LanternaTerminal terminal;
    private List<GUIcomponent> components;
    private TextColor color;

    public GUIRoot(LanternaTerminal terminal, TextColor backgroundColor) {
        this.terminal = terminal;
        this.color    = backgroundColor;
        components = new ArrayList<>();
    }

    public void addComponent(GUIcomponent component) {
        components.add(component);
    }

    public void draw() throws IOException {
        terminal.resizeIfNecessary();
        terminal.clear();

        TextGraphics buffer = terminal.getTerminalBuffer();

        buffer.fillRectangle(
                new TerminalPosition(0, 0),
                buffer.getSize(),
                new TextCharacter('A', color, color)
        );

        for (GUIcomponent i: components) {
            i.bondedDraw(buffer);
        }

        terminal.refresh();
    }
}
