package out_of_the_breach;

import out_of_the_breach.GUI.componentPosition.AbsComponentPosition;
import out_of_the_breach.GUI.GUIcomponent;
import out_of_the_breach.GUI.ScreenCorner;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.componentPosition.iGUIcomponentPosition;
import out_of_the_breach.model.*;

public class BoardTilesComponent extends GUIcomponent {
    private GameModel gameModel;

    public BoardTilesComponent(GameModel gameModel, iGUIcomponentPosition pos) {
        super(new TerminalSize(40, 24), pos);
        this.gameModel = gameModel;
    }

    public BoardTilesComponent(GameModel gameModel) {
        this(gameModel, new AbsComponentPosition(0, 0, ScreenCorner.TopLeft));
    }

    private void drawPlain(TextGraphics buffer) {
        buffer.fillRectangle(
                new TerminalPosition(0, 0),
                buffer.getSize(),
                new TextCharacter('P', new TextColor.RGB(132,192,17), new TextColor.RGB(112,172,17))
        );
    }

    private void drawMountain(TextGraphics buffer) {
        buffer.fillRectangle(
                new TerminalPosition(0, 0),
                buffer.getSize(),
                new TextCharacter('M', new TextColor.RGB(178, 156, 100), new TextColor.RGB(168, 136, 87))
        );
    }

    private void drawEntity(TextGraphics buffer, String initials) {
        buffer.putString(1, 1, initials);
    }

    @Override
    public void draw(TextGraphics buffer) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int offsetX = x * 5;
                int offsetY = y * 3;

                TerminalPosition offset = new TerminalPosition(offsetX, offsetY);
                TerminalSize size = new TerminalSize(5, 3);

                TextGraphics tileBox = buffer.newTextGraphics(offset, size);

                Position pos;
                try {
                    pos = new Position(x, y);
                }
                catch (OutsideOfTheGrid o) {
                    // Impossible to reach
                    continue;
                }

                //TODO: Refactor this
                //Should the tiles know how to draw themselves?
                switch (gameModel.getTiles().get(pos.getLinearMatrixPosition())) {
                    case PLAIN:
                        drawPlain(tileBox);
                        break;
                    case MOUNTAIN:
                        drawMountain(tileBox);
                        break;
                }
                if (gameModel.tileOccupied(pos)) {
                    Entity entity = gameModel.getEntityAt(pos);
                    drawEntity(tileBox, entity.getInitials());
                }
            }
        }
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
