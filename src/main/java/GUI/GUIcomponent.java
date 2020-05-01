package GUI;

import GUI.iGUIcomponentPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public abstract class GUIcomponent {
    private final TerminalSize componentSize;
    private final iGUIcomponentPosition position;
    private boolean selectable;
    private boolean selected;

    public GUIcomponent(TerminalSize componentSize, iGUIcomponentPosition position, boolean selectable) {
        this.componentSize = componentSize;
        this.position = position;
        this.selectable = selectable;
        this.selected = false;
    }

    public GUIcomponent(TerminalSize componentSize, iGUIcomponentPosition position) {
        this(componentSize, position, false);
    }

    public void bondedDraw(TextGraphics buffer) {
        TextGraphics bondedBuffer = buffer.newTextGraphics(
                position.getActualOffset(buffer, componentSize),
                componentSize
        );

        draw(bondedBuffer);
    }

    public TerminalSize getComponentSize() {
        return componentSize;
    }

    public iGUIcomponentPosition getPosition() {
        return position;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     *
     * @param stroke
     * @return Whether or not this component whishes to be selected
     */
    public boolean processKeystroke(KeyStroke stroke) {
        return false;
    }

    public abstract void draw(TextGraphics buffer);

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
