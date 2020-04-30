import GUI.CenteredComponentPosition;
import GUI.GUIcomponent;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Model;

public class BoardManager extends GUIcomponent {
    BoardTilesComponent tiles;
    Model model;

    public BoardManager(BoardTilesComponent tiles, Model model) {
        super(new TerminalSize(40, 24), new CenteredComponentPosition());
        this.tiles = tiles;
        this.model = model;
    }

    @Override
    public void draw(TextGraphics buffer) {
        tiles.draw(buffer);
    }
}
