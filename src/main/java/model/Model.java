package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<TerrainTile> terrain;
    private List<Entity> entities;

    public Model() {
        this.terrain = new ArrayList<>(64);
        this.entities = new ArrayList<>(64);
    }

    public Model(List<TerrainTile> terrain, List<Entity> entities) {
        this.terrain = terrain;
        this.entities = entities;
    }

    public boolean hasTerrain(Position pos) {
        return true;
    }

    public TerrainTile getTerrainAt(Position pos) {
        return null;
    }

    public boolean hasEntity(Position pos) {
        return true;
    }

    public Entity getEntityAt(Position pos) {
        return null;
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
            this.entities.set(entity.getPosition().getLinearMatrixPosition(), entity);
        }
        else {
            // exception
        }
    }

    public void draw() {
        for (TerrainTile terrainTile : this.terrain) {
            //terrainTile.draw(); // TROUBLE
        }
        for (Entity entity : this.entities) {
            entity.draw();
        }
    }

    public void inflictDamage(Position pos, int damage) {
        if (this.hasEntity(pos)) {
            Entity entity = this.getEntityAt(pos);
            entity.takeDamage(damage);
            if (entity.isDead()) {
                this.entities.remove(entity);
            }
        }
    }
}
