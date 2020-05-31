package out_of_the_breach.GUI.componentPosition;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HorizontallyCenteredComponentPosition implements iGUIcomponentPosition {
    private int offsetY;

    public HorizontallyCenteredComponentPosition(int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    public TerminalPosition getActualOffset(TextGraphics buffer, TerminalSize componentSize) {
        int offsetX = (buffer.getSize().getColumns() - componentSize.getColumns()) / 2;
        return new TerminalPosition(offsetX, offsetY);
    }
}
