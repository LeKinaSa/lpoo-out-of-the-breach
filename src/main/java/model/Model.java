package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<TerrainTile> tiles;
    private List<Ally> allies;
    private List<Enemy> enemies;
    private List<City> cities;

    public Model(
            List<TerrainTile> tiles,
            List<Ally> allies,
            List<Enemy> enemies,
            List<City> cities
    ) {
        this.tiles = tiles;
        this.allies = allies;
        this.enemies = enemies;
        this.cities = cities;
        //TODO: verificar input
    }

    public List<TerrainTile> getTiles() {
        return tiles;
    }

    public List<Ally> getAllies() {
        return allies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<City> getCities() {
        return cities;
    }

    public boolean tileOccupied(Position pos) {
        return getEntityAt(pos) != null;
    }

    public Entity getEntityAt(Position pos) {
        for (Entity i : cities) {
            if (i.getPosition().same(pos)) {
                return i;
            }
        }

        for (Entity i : allies) {
            if (i.getPosition().same(pos)) {
                return i;
            }
        }

        for (Entity i : enemies) {
            if (i.getPosition().same(pos)) {
                return i;
            }
        }
        return null;
    }
}
