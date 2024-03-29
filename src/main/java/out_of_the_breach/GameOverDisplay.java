package out_of_the_breach;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.componentPosition.CenteredComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;

public class GameOverDisplay extends ColorfulRectangle {
    private String text;

    public GameOverDisplay() {
        super(
                new TerminalSize(16, 3),
                new CenteredComponentPosition(),
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
