package out_of_the_breach;

import out_of_the_breach.GUI.componentPosition.AbsComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.ScreenCorner;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.model.TerrainTile;

public class TerrainDescriptionComponent extends ColorfulRectangle {
    private String text;

    public TerrainDescriptionComponent() {
        super(
                new TerminalSize(20, 3),
                new AbsComponentPosition(0, 0, ScreenCorner.BottomRight),
                new TextColor.RGB(0,59,92)
        );
        text = "";
        setEnabled(false);
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

    public void updateDescription(TerrainTile tile) {
        switch (tile) {
            case PLAIN:
                text = "Plain";
                break;
            case MOUNTAIN:
                text = "Mountain";
                break;
        }
    }
}
