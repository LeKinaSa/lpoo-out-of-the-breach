package out_of_the_breach.GUI.componentPosition;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public interface iGUIcomponentPosition {
    TerminalPosition getActualOffset(TextGraphics buffer, TerminalSize componentSize);
}
