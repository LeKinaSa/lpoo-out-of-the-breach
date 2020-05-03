package out_of_the_breach;

import out_of_the_breach.GUI.AbsComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.ScreenCorner;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PowerGridComponent extends ColorfulRectangle {
    int powerGridLevel;
    int percentage;

    public PowerGridComponent() {
        super(
                new TerminalSize(40, 2),
                new AbsComponentPosition(0, 0, ScreenCorner.TopLeft),
                new TextColor.RGB(0,59,92)
        );

        powerGridLevel = 0;
        percentage     = 0;
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0,59,92));
        buffer.enableModifiers(SGR.BOLD);

        buffer.putString(1, 0, "POWER");
        buffer.putString(1, 1, "GRID");

        buffer.putString(28, 0, "RESIST");
        buffer.putString(28, 1, "CHANCE");

        buffer.putString(35, 1, new String(String.valueOf(percentage)) + "%");

        drawEnergyBar(buffer);
    }

    private void drawEnergyBar(TextGraphics buffer) {
        buffer.fillRectangle(
                new TerminalPosition(7, 0),
                new TerminalSize(powerGridLevel * 2, 2),
                new TextCharacter('A', new TextColor.RGB(204, 204, 0), new TextColor.RGB(204, 204, 0))
        );
    }

    public int getPowerGridLevel() {
        return powerGridLevel;
    }

    public void setPowerGridLevel(int powerGridLevel) {
        this.powerGridLevel = powerGridLevel;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
