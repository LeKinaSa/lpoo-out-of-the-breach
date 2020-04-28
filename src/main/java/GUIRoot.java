import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIRoot {
    private LanternaTerminal terminal;
    private List<GUIcomponent> components;

    public GUIRoot(LanternaTerminal terminal) {
        this.terminal = terminal;
        components = new ArrayList<>();
    }

    public void addComponent(GUIcomponent component) {
        components.add(component);
    }

    public void draw() throws IOException {
        terminal.resizeIfNecessary();
        terminal.clear();

        for (GUIcomponent i: components) {
            i.bondedDraw(terminal.getTerminalBuffer());
        }

        terminal.refresh();
    }
}
