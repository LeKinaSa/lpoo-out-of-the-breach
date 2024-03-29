package out_of_the_breach;

import out_of_the_breach.GUI.componentPosition.AbsComponentPosition;
import out_of_the_breach.GUI.ColorfulRectangle;
import out_of_the_breach.GUI.ScreenCorner;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.model.GameModel;

public class EndTurnButton extends ColorfulRectangle {
    private TooltipComponent tooltip;
    private GameModel gameModel;

    public EndTurnButton(GameModel gameModel, TooltipComponent tooltip) {
        super(
                new TerminalSize(15, 3),
                new AbsComponentPosition(0, 3, ScreenCorner.TopLeft),
                new TextColor.RGB(0, 59, 92),
                true
        );
        this.tooltip = tooltip;
        this.gameModel = gameModel;
    }

    @Override
    public void draw(TextGraphics buffer) {
        super.draw(buffer);

        buffer.setBackgroundColor(new TextColor.RGB(0, 59, 92));
        buffer.enableModifiers(SGR.BOLD);

        buffer.putString(3, 1, "END TURN");

        if (isSelected()) {
            tooltip.setText("Press Enter key to end turn");
            drawBorder(buffer, new TextColor.RGB(255, 255, 255));
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {
        switch (stroke.getKeyType()) {
            case Enter:
                gameModel.executeAttack();
                gameModel.planAttack();
                gameModel.resetHeroes();
                return true;
            case ArrowRight:
                return false;
            default:
                return true;
        }
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}