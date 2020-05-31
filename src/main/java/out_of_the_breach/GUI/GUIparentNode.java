package out_of_the_breach.GUI;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.componentPosition.iGUIcomponentPosition;

import java.util.ArrayList;
import java.util.List;

public class GUIparentNode extends GUIcomponent {
    protected List<GUIcomponent> components;
    protected int selectedComponent;

    public GUIparentNode(TerminalSize componentSize, iGUIcomponentPosition position, boolean selectable) {
        super(componentSize, position, selectable);
        components = new ArrayList<>();
        selectedComponent = 0;
    }

    public GUIparentNode(TerminalSize componentSize, iGUIcomponentPosition position) {
        this(componentSize, position, false);
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
            GUIcomponent component = components.get(selectedComponent);

            if (component.isSelectable()) {
                component.setSelected(true);
            } else {
                continue;
            }

            if (component.processKeystroke(stroke)) {
                return true;
            } else {
                component.setSelected(false);
            }
        }
        selectedComponent = 0;
        return false;
    }
}
