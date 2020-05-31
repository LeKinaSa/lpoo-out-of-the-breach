package out_of_the_breach.GUI.componentPosition;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.ScreenCorner;

public class AbsComponentPosition implements iGUIcomponentPosition {
    private final int x;
    private final int y;
    private final ScreenCorner corner;

    public AbsComponentPosition(int x, int y, ScreenCorner corner) {
        this.x = x;
        this.y = y;
        this.corner = corner;
    }

    @Override
    public TerminalPosition getActualOffset(TextGraphics buffer, TerminalSize componentSize) {
        int offsetX;
        int offsetY;
        boolean top  = (corner == ScreenCorner.TopLeft)    || (corner == ScreenCorner.TopRight);
        boolean left = (corner == ScreenCorner.BottomLeft) || (corner == ScreenCorner.TopLeft);

        if (left) {
            offsetX = x;
        } else {
            offsetX = buffer.getSize().getColumns() - x - componentSize.getColumns();
        }

        if (top) {
            offsetY = y;
        } else {
            offsetY = buffer.getSize().getRows() - y - componentSize.getRows();
        }

        return new TerminalPosition(offsetX, offsetY);
    }
}
