package out_of_the_breach;

import out_of_the_breach.GUI.*;
import com.googlecode.lanterna.TextColor;
import out_of_the_breach.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, OutsideOfTheGrid {

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

        List<Hero> allies = new ArrayList<>();
        List<AttackStrategy> strats = new ArrayList<>();
        strats.add(new MeleeAttack(3, AttackDirection.NORTH));
        strats.add(new MeleeAttack(3, AttackDirection.EAST ));
        strats.add(new MeleeAttack(3, AttackDirection.SOUTH));
        strats.add(new MeleeAttack(3, AttackDirection.WEST ));
        allies.add(new Tank(new Position(0, 1), 2, 3, strats));

        List<Enemy> enemies = new ArrayList<>();
        Bug mike = new Bug(new Position(0, 2), 2, 2);
        mike.setAttackDirection(AttackDirection.NORTH);
        enemies.add(mike);

        LanternaTerminal t = new LanternaTerminal(110, 40);
        GUIRoot root       = new GUIRoot(t, new TextColor.RGB(40, 40, 40));
        Model model        = new Model(tiles, enemies, allies, cities);

        TooltipComponent tooltip = new TooltipComponent();
        TerrainDescriptionComponent terrainDescription = new TerrainDescriptionComponent();
        EntityInfoComponent eic = new EntityInfoComponent();

        root.addComponent(
                new BoardManager(new BoardTilesComponent(model), model, new BoardGUIOverlay(model, tooltip, terrainDescription, eic), tooltip)
        );

        root.addComponent(
                new PowerGridComponent()
        );

        root.addComponent(
                new EndTurnButton(model, tooltip)
        );

        root.addComponent(
                new EnemyRoutedComponent()
        );

        root.addComponent(
                tooltip
        );

        root.addComponent(
                terrainDescription
        );

        root.addComponent(
                eic
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