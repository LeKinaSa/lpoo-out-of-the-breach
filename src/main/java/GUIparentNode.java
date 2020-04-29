import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class GUIparentNode extends GUIcomponent {
    private List<GUIcomponent> components;

    public GUIparentNode(TerminalSize componentSize, iGUIcomponentPosition position) {
        super(componentSize, position);
        components = new ArrayList<>();
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
}
