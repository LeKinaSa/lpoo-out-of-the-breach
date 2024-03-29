package out_of_the_breach.GUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.componentPosition.iGUIcomponentPosition;

public class ColorfulRectangle extends GUIparentNode {
    private TextColor color;

    public ColorfulRectangle(TerminalSize componentSize, iGUIcomponentPosition position, TextColor color, boolean selectable) {
        super(componentSize, position, selectable);
        this.color = color;
    }

    public ColorfulRectangle(TerminalSize componentSize, iGUIcomponentPosition position, TextColor color) {
        super(componentSize, position);
        this.color = color;
    }

    @Override
    public void draw(TextGraphics buffer) {
        buffer.fillRectangle(
                new TerminalPosition(0, 0),
                buffer.getSize(),
                new TextCharacter('A', color, color)
        );
    }
}
