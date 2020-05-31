package out_of_the_breach.GUI;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.componentPosition.iGUIcomponentPosition;
import out_of_the_breach.GUI.componentSelectionStrategy.ParentNodeSelectionStrategy;
import out_of_the_breach.GUI.componentSelectionStrategy.iComponentSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class GUIparentNode extends GUIcomponent {
    protected List<GUIcomponent> components;
    private iComponentSelectionStrategy strat;

    public GUIparentNode(
            TerminalSize componentSize,
            iGUIcomponentPosition position,
            boolean selectable,
            iComponentSelectionStrategy strat
    ) {
        super(componentSize, position, selectable);
        components = new ArrayList<>();
        this.strat = strat;
    }

    public GUIparentNode(TerminalSize componentSize, iGUIcomponentPosition position, boolean selectable) {
        this(componentSize, position, selectable, new ParentNodeSelectionStrategy());
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
        return strat.processKeystroke(stroke, components);
    }
}
