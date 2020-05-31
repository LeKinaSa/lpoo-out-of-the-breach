package out_of_the_breach.GUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import out_of_the_breach.GUI.componentSelectionStrategy.RootSelectionStrategy;
import out_of_the_breach.GUI.componentSelectionStrategy.iComponentSelectionStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIRoot extends GUIparentNode {
    private LanternaTerminal terminal;
    private TextColor color;


    public GUIRoot(LanternaTerminal terminal, TextColor backgroundColor, iComponentSelectionStrategy strat) {
        super(null, null, true, strat);
        this.terminal = terminal;
        this.color    = backgroundColor;
    }

    public GUIRoot(LanternaTerminal terminal, TextColor backgroundColor) {
        this(terminal, backgroundColor, new RootSelectionStrategy());
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
}
