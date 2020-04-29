package GUI;

import GUI.iGUIcomponentPosition;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public class CenteredComponentPosition implements iGUIcomponentPosition {

    @Override
    public TerminalPosition getActualOffset(TextGraphics buffer, TerminalSize componentSize) {
        int offsetX = (buffer.getSize().getColumns() - componentSize.getColumns()) / 2;
        int offsetY = (buffer.getSize().getRows()    - componentSize.getRows())    / 2;
        return new TerminalPosition(offsetX, offsetY);
    }
}
