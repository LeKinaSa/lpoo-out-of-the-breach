import GUI.AbsComponentPosition;
import GUI.GUIcomponent;
import GUI.ScreenCorner;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import model.*;

public class BoardGUIOverlay extends GUIcomponent {
    private enum SelectorMode {
        NORMAL,
        MOVE,
        ATTACK
    }

    int x;
    int y;
    Model model;
    TooltipComponent tooltip;
    TerrainDescriptionComponent terrainDescription;
    EntityInfoComponent eic;

    public BoardGUIOverlay(Model model, TooltipComponent tooltip, TerrainDescriptionComponent terrainDescription, EntityInfoComponent eic) {
        super(new TerminalSize(40, 24), new AbsComponentPosition(0, 0, ScreenCorner.TopLeft), true);
        this.model = model;
        this.tooltip = tooltip;
        this.terrainDescription = terrainDescription;
        this.eic = eic;

        x = 0;
        y = 0;
    }

    private void drawTileHighlighter(TextGraphics buffer, TextColor color, int x, int y) {
        int offsetX = x * 5;
        int offsetY = y * 3;
        buffer.drawRectangle(
                new TerminalPosition(offsetX, offsetY),
                new TerminalSize(5, 3),
                new TextCharacter('M', color, color)
        );
    }

    private void drawSelector(TextGraphics buffer) {
        drawTileHighlighter(buffer, new TextColor.RGB(255, 255, 255), x, y);
    }

    @Override
    public void draw(TextGraphics buffer) {
        drawSelector(buffer);

        tooltip.setText("Use the arrow keys to move around");

        Entity entity;
        try {
            entity = model.getEntityAt(new Position(x, y));
        } catch (OutsideOfTheGrid e) {
            entity = null; // This should never happen
        }

        if (entity instanceof Enemy) {
            Enemy enemy = (Enemy) entity;
            drawDamageMatrix(buffer, enemy.previewAttack());
        } else if (entity instanceof Hero) {
            Hero hero = (Hero) entity;
            if (hero.getHasEndedTurn()) {
                tooltip.setText("This hero has already finished its turn!");
            } else if (hero.getHasMoved()) {
                tooltip.setText("Press A to attack");
            } else {
                tooltip.setText("Press M to move, A to attack");
            }
        }
    }

    private void incrementY() {
        y = (y == 7) ? 7 : y + 1;
    }

    private void decrementY() {
        y = (y == 0) ? 0 : y - 1;
    }

    private void incrementX() {
        x = (x == 7) ? 7 : x + 1;
    }

    private void decrementX() {
        x = (x == 0) ? 0 : x - 1;
    }

    private void drawDamageMatrix(TextGraphics buffer, DamageMatrix matrix) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Position p = null;
                try {
                    p = new Position(x, y);
                } catch (OutsideOfTheGrid e) {
                    // Should never happen
                }

                if (matrix.getDamage(p) > 0) {
                    drawTileHighlighter(buffer, new TextColor.RGB(255, 0, 0), x, y);
                }
            }
        }
    }

    private boolean processArrowKeys(KeyStroke stroke) {
        switch (stroke.getKeyType()) {
            case ArrowUp:
                decrementY();
                return true;
            case ArrowDown:
                incrementY();
                return true;
            case ArrowLeft:
                decrementX();
                return true;
            case ArrowRight:
                incrementX();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {
        if (processArrowKeys(stroke)) {
            terrainDescription.updateDescription(model.getTiles().get(y * 8 + x));

            Entity entity;
            try {
                entity = model.getEntityAt(new Position(x, y));
            } catch (OutsideOfTheGrid e) {
                entity = null; // This should never happen
            }

            if (entity == null) {
                eic.setEnabled(false);
            } else {
                eic.setEnabled(true);
                eic.setSelectedEntity(entity);
            }

            return true;
        }

        return super.processKeystroke(stroke);
    }

    @Override
    public void setSelected(boolean selected) {
        terrainDescription.setEnabled(selected);
        super.setSelected(selected);
    }
}
