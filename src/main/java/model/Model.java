package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<TerrainTile> tiles;
    private List<Enemy> enemies;
    private List<Ally> allies;
    private List<City> cities;

    public Model() {
        this.tiles = new ArrayList<>(64);
        this.enemies = new ArrayList<>();
        this.allies = new ArrayList<>();
        this.cities = new ArrayList<>();
    }

    public Model(List<TerrainTile> tiles, List<Enemy> enemies, List<Ally> allies, List<City> cities) {
        this.tiles = tiles;
        this.enemies = enemies;
        this.allies = allies;
        this.cities = cities;
    }

    public boolean hasTerrain(Position pos) {
        return this.tiles.get(pos.getLinearMatrixPosition()) != null;
    }

    public TerrainTile getTerrainAt(Position pos) {
        return this.tiles.get(pos.getLinearMatrixPosition());
    }


    public void addTerrain(TerrainTile terrainTile, Position pos) {
        if (!this.hasTerrain(pos)) {
            this.tiles.set(pos.getLinearMatrixPosition(), terrainTile);
        }
        else {
            // exception
        }
    }

    /*public void addEntity(Entity entity) {
        if (!this.hasEntity(entity.getPosition())) {
            //this.entities.set(entity.getPosition().getLinearMatrixPosition(), entity);
        }
        else {
            // exception
        }
    }*/

    /*public void draw() {
        for (TerrainTile terrainTile : this.terrain) {
            //terrainTile.draw(); // TROUBLE
        }
        for (Entity entity : this.entities) {
            entity.draw();
        }
    }*/

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

    public void inflictDamage(Position pos, int damage) {
        if (this.tileOccupied(pos)) {
            Entity entity = this.getEntityAt(pos);
            entity.takeDamage(damage);
            if (entity.isDead()) {
                //this.entities.remove(entity);
            }
        }
    }

    public List<TerrainTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<TerrainTile> tiles) {
        this.tiles = tiles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Ally> getAllies() {
        return allies;
    }

    public void setAllies(List<Ally> allies) {
        this.allies = allies;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
