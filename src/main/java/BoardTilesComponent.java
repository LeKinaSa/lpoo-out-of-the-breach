import GUI.AbsComponentPosition;
import GUI.GUIcomponent;
import GUI.ScreenCorner;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.*;

public class BoardTilesComponent extends GUIcomponent {
    private Model model;

    public BoardTilesComponent(Model model) {
        super(new TerminalSize(40, 24), new AbsComponentPosition(0, 0, ScreenCorner.TopLeft));
        this.model = model;
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

    private void drawCity(TextGraphics buffer) {
        buffer.putString(1, 1, "CTY");
    }

    private void drawAlly(TextGraphics buffer) {
        buffer.putString(1, 1, "HRO");
    }

    private void drawEnemy(TextGraphics buffer) {
        buffer.putString(1, 1, "BUG");
    }

    @Override
    public void draw(TextGraphics buffer) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int offsetX = x * 5;
                int offsetY = y * 3;
                int linearOffset = y*8 + x;

                TerminalPosition offset = new TerminalPosition(offsetX, offsetY);
                TerminalSize size = new TerminalSize(5, 3);

                TextGraphics tileBox = buffer.newTextGraphics(offset, size);

                //TODO: Refactor this
                //Should the tiles know how to draw themselves?
                switch (model.getTiles().get(linearOffset)) {
                    case PLAIN:
                        drawPlain(tileBox);
                        break;
                    case MOUNTAIN:
                        drawMountain(tileBox);
                        break;
                }

                try {
                    if (model.tileOccupied(new Position(x, y))) {
                        Entity entity = model.getEntityAt(new Position(x, y));
                        if (entity instanceof City) {
                            drawCity(tileBox);
                        } else if (entity instanceof Hero) {
                            drawAlly(tileBox);
                        } else if (entity instanceof Enemy) {
                            drawEnemy(tileBox);
                        }
                    }
                } catch (OutsideOfTheGrid e) {

                }

            }
        }
    }
}
