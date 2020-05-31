package out_of_the_breach;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.componentPosition.AbsComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.ScreenCorner;
import out_of_the_breach.GUI.componentPosition.HorizontallyCenteredComponentPosition;

public class LevelDisplay extends ColorfulRectangle {
    private int level;

    public LevelDisplay() {
        super(
                new TerminalSize(47, 5),
                new HorizontallyCenteredComponentPosition(0),
                new TextColor.RGB(0,59,92)
        );
        level = 1;
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0,59,92));
        buffer.enableModifiers(SGR.BOLD);

        buffer.putString(
                3,
                1,
                "Use arrows keys and Enter to play a level"
        );

        buffer.putString(
                3,
                3,
                "                 Level " + new String(String.valueOf(level))
        );
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
