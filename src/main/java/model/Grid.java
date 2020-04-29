package model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<TerrainTile> terrain;
    private List<Entity> entities;

    public Grid() {
        this.terrain = new ArrayList<>(64);
        this.entities = new ArrayList<>(64);
    }

    public Grid(List<TerrainTile> terrain, List<Entity> entities) {
        this.terrain = terrain;
        this.entities = entities;
    }
        }
    }

    public void draw() {
        for (TerrainTile terrainTile : this.firstLayer) {
            terrainTile.draw();
        }
        for (GridElement gridElement : this.secondLayer) {
            gridElement.draw();
        }
        this.ally.draw();
    }

    public void inflictDamage(Position pos, int damage) {
        if (pos.insideRectangle(new Position(0, 0), new Position(this.sizeX, this.sizeY))) {
            for (GridElement element : this.secondLayer) {
                if (element.getPos().same(pos)) {
                    element.takeDamage(damage);
                }
            }
        }
    }

    public void cleanDeadBodies() {
        for (GridElement element : this.secondLayer) {
            if (element.isDead()) {
                this.secondLayer.remove(element);
            }
        }
    }
}
