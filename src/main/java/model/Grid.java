package model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<TerrainTile> terrain;
    private List<Entity> entities;

    public Grid() {

    public void addSecondLayerElement(GridElement element) {
        if (!element.insideGrid(this.sizeX, this.sizeY)) {
            return;
            // exception ?
        this.terrain = new ArrayList<>(64);
        this.entities = new ArrayList<>(64);
    }

    public Grid(List<TerrainTile> terrain, List<Entity> entities) {
        this.terrain = terrain;
        this.entities = entities;
    }
        }
        this.secondLayer.add(element);
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
