import GUI.CenteredComponentPosition;
import GUI.GUIcomponent;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Model;

import java.io.Console;

public class BoardManager extends GUIcomponent {
    BoardTilesComponent tiles;
    BoardGUIOverlay overlay;
    Model model;

    public BoardManager(BoardTilesComponent tiles, Model model, BoardGUIOverlay overlay) {
        super(new TerminalSize(40, 24), new CenteredComponentPosition(), true);
        this.tiles = tiles;
        this.model = model;
        this.overlay = overlay;
    }

    private void drawBoundingBox(TextGraphics buffer, TextColor color) {
        buffer.drawRectangle(
                new TerminalPosition(0, 0),
                buffer.getSize(),
                new TextCharacter('M', color, color)
        );
    }

    @Override
    public void draw(TextGraphics buffer) {
        tiles.draw(buffer);

        if (isSelected()) {
            if (overlay.isSelected()) { // If it's in "overlay mode", draw overlay
                overlay.draw(buffer);
            } else {
                drawBoundingBox(buffer, new TextColor.RGB(255, 255, 255));
            }
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {

        if (!overlay.isSelected()) {
            if (stroke.getKeyType() == KeyType.Enter) {
                overlay.setSelected(true);
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

}
