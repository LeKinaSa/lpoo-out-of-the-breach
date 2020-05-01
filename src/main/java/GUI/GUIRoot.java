package GUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIRoot {
    private LanternaTerminal terminal;
    private List<GUIcomponent> components;
    private TextColor color;
    private int selectedComponent;


    public GUIRoot(LanternaTerminal terminal, TextColor backgroundColor) {
        this.terminal = terminal;
        this.color    = backgroundColor;
        components = new ArrayList<>();
        selectedComponent = 0;
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

    public boolean processInput() throws IOException {
        KeyStroke stroke = terminal.pollInput();
        if (stroke == null) {
            return true;
        }
        if (stroke.getKeyType() == KeyType.EOF) {
            terminal.close();
            return false;
        }

        processKeystroke(stroke);
        return true;
    }

    public boolean processKeystroke(KeyStroke stroke) { //TODO: This loop isn't very readable
        for (int i = 0; i < components.size(); i++, selectedComponent = (selectedComponent + 1) % components.size()) {
            GUIcomponent component = components.get(selectedComponent);
            if (component.isSelectable() && component.processKeystroke(stroke)) {
                return true;
            }
        }
        selectedComponent = 0;
        return false;
    }
}
