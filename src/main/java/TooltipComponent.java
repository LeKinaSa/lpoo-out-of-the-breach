import GUI.AbsComponentPosition;
import GUI.ColorfulRectangle;
import GUI.ScreenCorner;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class TooltipComponent extends ColorfulRectangle {
    private String text;

    public TooltipComponent() {
        super(
                new TerminalSize(46, 3),
                new AbsComponentPosition(0, 0, ScreenCorner.BottomLeft),
                new TextColor.RGB(0,59,92)
        );
        text = "";
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0,59,92));
        buffer.enableModifiers(SGR.BOLD);

        buffer.putString(
                3,
                1,
                text
        );
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
