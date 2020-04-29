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
        return true;
    }

    public Entity getEntityAt(Position pos) {
        return null;
    }
}
