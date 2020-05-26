package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static out_of_the_breach.model.TerrainTile.*;

public class Level6 extends Level {
    @Override
    protected List<TerrainTile> getLevelTiles() {
        List<TerrainTile> tiles = new ArrayList<>(Collections.nCopies(64, PLAIN));
        tiles.set( 7, MOUNTAIN);
        tiles.set(15, MOUNTAIN);
        tiles.set(36, MOUNTAIN);
        tiles.set(37, MOUNTAIN);
        tiles.set(43, MOUNTAIN);
        tiles.set(44, MOUNTAIN);
        return tiles;
    }

    @Override
    protected List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(0, 3), 6));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(0, 4), 6));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(7, 2), 5));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(7, 3), 5));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(7, 4), 5));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return cities;
    }

    @Override
    protected List<Hero> getLevelAllies() {
        List<Hero> allies = new ArrayList<>();

        try {
            allies.add(new Tank(new Position(6, 6), 5, 2, 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Tank(new Position(5, 6), 8, 1, 1));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(0, 1), 2, 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(6, 0), 3, 6));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return allies;
    }

    @Override
    protected List<Enemy> getLevelEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        try {
            enemies.add(new Lizard(new Position(2, 1), 10, 1));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            enemies.add(new Lizard(new Position(3, 4), 3, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            enemies.add(new Lizard(new Position(1, 6), 5, 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return enemies;
    }
}
