package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<TerrainTile> terrain;
    private List<Enemy> enemies;
    private List<Ally> allies;
    private List<City> cities;

    public Model() {
        this.terrain = new ArrayList<>(64);
        this.enemies = new ArrayList<>();
        this.allies = new ArrayList<>();
        this.cities = new ArrayList<>();
    }

    public Model(List<TerrainTile> terrain, List<Enemy> enemies, List<Ally> allies, List<City> cities) {
        this.terrain = terrain;
        this.enemies = enemies;
        this.allies = allies;
        this.cities = cities;
    }

    public boolean hasTerrain(Position pos) {
        return this.terrain.get(pos.getLinearMatrixPosition()) != null;
    }

    public TerrainTile getTerrainAt(Position pos) {
        return this.terrain.get(pos.getLinearMatrixPosition());
    }

    public boolean hasEntity(Position pos) {
        //return this.entities.get(pos.getLinearMatrixPosition()) != null;
    }

    public Entity getEntityAt(Position pos) {
        //return this.entities.get(pos.getLinearMatrixPosition());
    }

    public void addTerrain(TerrainTile terrainTile, Position pos) {
        if (!this.hasTerrain(pos)) {
            this.terrain.set(pos.getLinearMatrixPosition(), terrainTile);
        }
        else {
            // exception
        }
    }

    public void addEntity(Entity entity) {
        if (!this.hasEntity(entity.getPosition())) {
            //this.entities.set(entity.getPosition().getLinearMatrixPosition(), entity);
        }
        else {
            // exception
        }
    }

    /*public void draw() {
        for (TerrainTile terrainTile : this.terrain) {
            //terrainTile.draw(); // TROUBLE
        }
        for (Entity entity : this.entities) {
            entity.draw();
        }
    }*/

    public void inflictDamage(Position pos, int damage) {
        if (this.hasEntity(pos)) {
            Entity entity = this.getEntityAt(pos);
            entity.takeDamage(damage);
            if (entity.isDead()) {
                //this.entities.remove(entity);
            }
        }
    }
}
