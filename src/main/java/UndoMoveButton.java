import GUI.AbsComponentPosition;
import GUI.ColorfulRectangle;
import GUI.ScreenCorner;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class UndoMoveButton extends ColorfulRectangle {

    public UndoMoveButton() {
        super(
                new TerminalSize(15, 3),
                new AbsComponentPosition(17, 3, ScreenCorner.TopLeft),
                new TextColor.RGB(0,59,92)
        );
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0,59,92));
        buffer.enableModifiers(SGR.BOLD);

        buffer.putString(3, 1, "UNDO MOVE");
    }
}
