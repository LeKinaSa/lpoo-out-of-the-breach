package GUI;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class GUIparentNode extends GUIcomponent {
    private List<GUIcomponent> components;
    private int selectedComponent;

    public GUIparentNode(TerminalSize componentSize, iGUIcomponentPosition position) {
        super(componentSize, position);
        components = new ArrayList<>();
        selectedComponent = 0;
    }

    public void addComponent(GUIcomponent component) {
        components.add(component);
    }

    @Override
    public void draw(TextGraphics buffer) {
        for (GUIcomponent i: components) {
            i.bondedDraw(buffer);
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {
        for (; selectedComponent < components.size(); selectedComponent++) {
            if (components.get(selectedComponent).processKeystroke(stroke)) {
                return true;
            }
        }
        selectedComponent = 0;
        return false;
    }
}
