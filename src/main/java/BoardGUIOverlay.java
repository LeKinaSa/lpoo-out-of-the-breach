import GUI.AbsComponentPosition;
import GUI.GUIcomponent;
import GUI.ScreenCorner;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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

    Hero selectedHero;
    SelectorMode mode;
    int selectedAttack;

    public BoardGUIOverlay(Model model, TooltipComponent tooltip, TerrainDescriptionComponent terrainDescription, EntityInfoComponent eic) {
        super(new TerminalSize(40, 24), new AbsComponentPosition(0, 0, ScreenCorner.TopLeft), true);
        this.model = model;
        this.tooltip = tooltip;
        this.terrainDescription = terrainDescription;
        this.eic = eic;

        selectedHero = null;
        mode = SelectorMode.NORMAL;
        x = 0;
        y = 0;
        selectedAttack = 0;
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
        if (mode == SelectorMode.MOVE) {
            drawMovementMatrix(buffer, selectedHero.displayMove());
        }
        if (mode == SelectorMode.ATTACK) {
            drawDamageMatrix(buffer, selectedHero.getStrategies().get(selectedAttack).previewAttack(selectedHero.getPosition()));
        }
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

    private void drawMovementMatrix(TextGraphics buffer, MovementMatrix matrix) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Position p = null;
                try {
                    p = new Position(x, y);
                } catch (OutsideOfTheGrid e) {
                    // Should never happen
                }

                if (matrix.getMove(p)) {
                    drawTileHighlighter(buffer, new TextColor.RGB(0, 255, 0), x, y);
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

    public boolean processNormalKeystroke(KeyStroke stroke) {
        if (processArrowKeys(stroke)) {
            Entity entity;
            try {
                entity = model.getEntityAt(new Position(x, y));
            } catch (OutsideOfTheGrid e) {
                entity = null; // This should never happen
            }

            terrainDescription.updateDescription(model.getTiles().get(y * 8 + x));

            if (entity == null) {
                eic.setEnabled(false);
            } else {
                eic.setEnabled(true);
                eic.setSelectedEntity(entity);
            }
            return true;
        }

        Entity entity;
        try {
            entity = model.getEntityAt(new Position(x, y));
        } catch (OutsideOfTheGrid e) {
            entity = null; // This should never happen
        }

        if (entity instanceof Hero) {
            Hero hero = (Hero) entity;
            if (stroke.getCharacter() == 'm') {
                selectedHero = hero;
                mode = SelectorMode.MOVE;
            } else if (stroke.getCharacter() == 'a') {
                selectedHero = hero;
                mode = SelectorMode.ATTACK;
            }
        }

        return stroke.getKeyType() != KeyType.Escape;
    }

    public boolean processMoveKeystroke(KeyStroke stroke) {
        if (processArrowKeys(stroke)) {
            Entity entity;
            try {
                entity = model.getEntityAt(new Position(x, y));
            } catch (OutsideOfTheGrid e) {
                entity = null; // This should never happen
            }

            terrainDescription.updateDescription(model.getTiles().get(y * 8 + x));

            if (entity == null) {
                eic.setEnabled(false);
            } else {
                eic.setEnabled(true);
                eic.setSelectedEntity(entity);
            }
            return true;
        }

        if (stroke.getKeyType() == KeyType.Enter) {
            Position p;
            try {
                p = new Position(x, y);
            } catch (OutsideOfTheGrid e) {
                p = null; // This should never happen
            }

            if (selectedHero.withinRange(p) && (!p.same(selectedHero.getPosition()))) {
                selectedHero.moveTo(p);
            } else {
                selectedHero = null;
            }
            mode = SelectorMode.NORMAL;
            return true;
        }

        if (stroke.getKeyType() == KeyType.Escape) {
            selectedHero = null;
            mode = SelectorMode.NORMAL;
            return true;
        }

        return true;
    }

    public boolean processAttackKeystroke(KeyStroke stroke) {
        int size = selectedHero.getStrategies().size();
        switch (stroke.getKeyType()) {
            case ArrowLeft:
                selectedAttack = (selectedAttack + size - 1) % size;
                return true;
            case ArrowRight:
                selectedAttack = (selectedAttack + 1) % size;
                return true;
            case Enter:
                selectedHero.attack(model, selectedAttack);
                selectedHero = null;
                selectedAttack = 0;
                mode = SelectorMode.NORMAL;
                return true;
            case Escape:
                selectedHero = null;
                selectedAttack = 0;
                mode = SelectorMode.NORMAL;
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {
        switch (mode) {
            case NORMAL:
                return processNormalKeystroke(stroke);
            case MOVE:
                return processMoveKeystroke(stroke);
            case ATTACK:
                return processAttackKeystroke(stroke);
            default:
                return false;
        }
    }

    @Override
    public void setSelected(boolean selected) {
        terrainDescription.setEnabled(selected);
        super.setSelected(selected);
    }
}
