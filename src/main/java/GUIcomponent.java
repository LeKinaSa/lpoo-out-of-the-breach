import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class GUIcomponent {
    private TerminalSize componentSize;
    private iGUIcomponentPosition position;

    public GUIcomponent(TerminalSize componentSize, iGUIcomponentPosition position) {
        this.componentSize = componentSize;
        this.position = position;
    }

    public void bondedDraw(TextGraphics buffer) {
        TextGraphics bondedBuffer = buffer.newTextGraphics(
                position.getActualOffset(buffer, componentSize),
                componentSize
        );

        draw(bondedBuffer);
    }

    public abstract void draw(TextGraphics buffer);
}
