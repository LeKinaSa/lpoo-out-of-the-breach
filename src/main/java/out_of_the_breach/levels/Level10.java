package out_of_the_breach.levels;

import out_of_the_breach.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static out_of_the_breach.model.TerrainTile.*;

public class Level10 extends Level {
    @Override
    protected List<TerrainTile> getLevelTiles() {
        List<TerrainTile> tiles = new ArrayList<>(Collections.nCopies(64, PLAIN));
        tiles.set( 1, MOUNTAIN);
        tiles.set(15, MOUNTAIN);
        tiles.set(23, MOUNTAIN);
        tiles.set(45, MOUNTAIN);
        tiles.set(53, MOUNTAIN);
        tiles.set(54, MOUNTAIN);
        tiles.set(61, MOUNTAIN);
        tiles.set(62, MOUNTAIN);
        return tiles;
    }

    @Override
    protected List<City> getLevelCities() {
        List<City> cities = new ArrayList<>();

        try {
            cities.add(new City(new Position(0, 0), 4));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(0, 4), 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(1, 4), 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(7, 4), 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            cities.add(new City(new Position(7, 5), 3));
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
            allies.add(new Tank(new Position(0, 7), 8, 1, 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Tank(new Position(4, 6), 8, 1, 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(2, 1), 2, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            allies.add(new Archer(new Position(7, 0), 2, 3));
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
            enemies.add(new Bug(new Position(0, 1), 4, 2));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            enemies.add(new Lizard(new Position(5, 2), 10, 1));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }
        try {
            enemies.add(new Dragon(new Position(2, 5), 4, 3));
        } catch (OutsideOfTheGrid outsideOfTheGrid) {
            //Impossible to get here
            outsideOfTheGrid.printStackTrace();
        }

        return enemies;
    }
}
