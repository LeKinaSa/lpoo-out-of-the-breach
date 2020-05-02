import GUI.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<TerrainTile> tiles = new ArrayList<>();
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.MOUNTAIN); tiles.add(TerrainTile.MOUNTAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.MOUNTAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);
        tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN); tiles.add(TerrainTile.PLAIN);

        List<City> cities = new ArrayList<>();
        cities.add(new City(new Position(0, 0), 2, 2));

        List<Ally> allies = new ArrayList<>();
        allies.add(new Ally(new Position(0, 1), 3));

        List<Enemy> enemies = new ArrayList<>();
        //enemies.add(new Enemy(new Position(0, 2), 2)

        LanternaTerminal t = new LanternaTerminal(110, 40);
        GUIRoot root       = new GUIRoot(t, new TextColor.RGB(40, 40, 40));
        Model model        = new Model(tiles, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        root.addComponent(
                new BoardManager(new BoardTilesComponent(model), model, new BoardGUIOverlay(model))
        );

        root.addComponent(
                new PowerGridComponent()
        );

        root.addComponent(
                new EndTurnButton()
        );

        root.addComponent(
                new UndoMoveButton()
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(15, 15),
                        new AbsComponentPosition(0, 7, ScreenCorner.TopLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new ColorfulRectangle(
                        new TerminalSize(50, 7),
                        new AbsComponentPosition(0, 0, ScreenCorner.BottomLeft),
                        new TextColor.RGB(0,6,177)
                )
        );

        root.addComponent(
                new EnemyRoutedComponent()
        );

        while (true) {
            Thread.sleep(10);

            if (!root.processInput()) {
                break;
            }
            root.draw();
        }
        
    }
}
