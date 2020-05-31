package out_of_the_breach;

import out_of_the_breach.GUI.componentPosition.CenteredComponentPosition;
import out_of_the_breach.GUI.GUIcomponent;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import out_of_the_breach.model.GameModel;

public class BoardManager extends GUIcomponent {
    BoardTilesComponent tiles;
    BoardGUIOverlay overlay;
    GameModel gameModel;
    TooltipComponent tooltip;

    public BoardManager(BoardTilesComponent tiles, GameModel gameModel, BoardGUIOverlay overlay, TooltipComponent tooltip) {
        super(new TerminalSize(40, 24), new CenteredComponentPosition(), true);
        this.tiles = tiles;
        this.gameModel = gameModel;
        this.overlay = overlay;
        this.tooltip = tooltip;
    }

    @Override
    public void draw(TextGraphics buffer) {
        tiles.draw(buffer);

        if (isSelected()) {
            if (overlay.isSelected()) { // If it's in "overlay mode", draw overlay
                overlay.draw(buffer);
            } else {
                tooltip.setText("Press Enter key to select board");
                drawBorder(buffer, new TextColor.RGB(255, 255, 255));
            }
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {

        if (!overlay.isSelected()) {
            if (stroke.getKeyType() == KeyType.Enter) {
                overlay.setSelected(true);
                overlay.processNormalKeystroke(new KeyStroke(KeyType.ArrowUp));
                return true;
            } else {
                return stroke.getKeyType() != KeyType.ArrowRight;
            }
        }

        if (overlay.processKeystroke(stroke)) {
            return true;
        } else {
            overlay.setSelected(false);
            return false;
        }
    }

    public void setGameModel(GameModel gameModel) {
        tiles.setGameModel(gameModel);
        overlay.setGameModel(gameModel);
        this.gameModel = gameModel;
    }

}
