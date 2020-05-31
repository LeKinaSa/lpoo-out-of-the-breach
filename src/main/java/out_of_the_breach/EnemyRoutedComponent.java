package out_of_the_breach;

import out_of_the_breach.GUI.componentPosition.AbsComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.ScreenCorner;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EnemyRoutedComponent extends ColorfulRectangle {
    private int nTurns;
    public EnemyRoutedComponent() {
        super(
                new TerminalSize(30, 3),
                new AbsComponentPosition(0, 0, ScreenCorner.TopRight),
                new TextColor.RGB(0,59,92)
        );

        nTurns = 0;
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0,59,92));
        buffer.enableModifiers(SGR.BOLD);

        buffer.putString(
                3,
                1,
                "ENEMY ROUTED IN " + new String(String.valueOf(nTurns)) + (nTurns == 1 ? " TURN" : " TURNS")
        );
    }

    public int getnTurns() {
        return nTurns;
    }

    public void setnTurns(int nTurns) {
        this.nTurns = nTurns;
    }
}
