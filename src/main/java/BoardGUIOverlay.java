import GUI.AbsComponentPosition;
import GUI.GUIcomponent;
import GUI.ScreenCorner;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Model;

public class BoardGUIOverlay extends GUIcomponent {
    int x;
    int y;
    Model model;

    public BoardGUIOverlay(Model model) {
        super(new TerminalSize(40, 24), new AbsComponentPosition(0, 0, ScreenCorner.TopLeft));
        this.model = model;

        x = 0;
        y = 0;
    }

    private void drawTileHighlighter(TextGraphics buffer, TextColor color, int x, int y) {
        buffer.drawRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(5, 3),
                new TextCharacter('M', color, color)
        );
    }

    private void drawSelector(TextGraphics buffer) {
        drawTileHighlighter(buffer, new TextColor.RGB(0, 255, 0), x, y);
    }

    @Override
    public void draw(TextGraphics buffer) {
        drawSelector(buffer);
    }
}
