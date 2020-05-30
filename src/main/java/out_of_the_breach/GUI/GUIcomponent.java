package out_of_the_breach.GUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public abstract class GUIcomponent {
    private final TerminalSize componentSize;
    private final iGUIcomponentPosition position;
    private boolean selectable;
    private boolean selected;
    private boolean enabled;

    public GUIcomponent(TerminalSize componentSize, iGUIcomponentPosition position, boolean selectable) {
        this.componentSize = componentSize;
        this.position = position;
        this.selectable = selectable;
        this.selected = false;
        this.enabled = true;
    }

    public GUIcomponent(TerminalSize componentSize, iGUIcomponentPosition position) {
        this(componentSize, position, false);
    }

    public void bondedDraw(TextGraphics buffer) {
        if (enabled) {
            TextGraphics bondedBuffer = buffer.newTextGraphics(
                    position.getActualOffset(buffer, componentSize),
                    componentSize
            );

            draw(bondedBuffer);
        }
    }

    public void drawBorder(TextGraphics buffer, TextColor color) {
        buffer.drawRectangle(
                new TerminalPosition(0, 0),
                buffer.getSize(),
                new TextCharacter('M', color, color)
        );
    }

    public TerminalSize getComponentSize() {
        return componentSize;
    }

    public iGUIcomponentPosition getPosition() {
        return position;
    }

    public boolean isSelectable() {
        if (!enabled) {
            return false;
        }
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
        if (!selectable) {
            selected = false;
        }
    }

    /**
     *
     * @param stroke
     * @return Whether or not this component wishes to be selected
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) {
            selected = false;
        }
    }
}
