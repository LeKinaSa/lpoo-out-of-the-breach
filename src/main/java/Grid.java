import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<GridElement> firstLayer;
    private List<GridElement> secondLayer;
    private int sizeX;
    private int sizeY;

    public Grid(int sizeX, int sizeY) {
        this.firstLayer = new ArrayList<>();
        this.secondLayer = new ArrayList<>();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public Grid(List<GridElement> firstLayer, List<GridElement> secondLayer) {
        this.firstLayer = firstLayer;
        this.secondLayer = secondLayer;
    }

    public void addFirstLayerElement(GridElement element) {
        if (!element.insideGrid(sizeX, sizeY)) {
            // exception ?
        }
        this.firstLayer.add(element);
    }

    public void addSecondLayerElement(GridElement element) {
        if (!element.insideGrid(sizeX, sizeY)) {
            // exception ?
        }
        this.secondLayer.add(element);
    }

    public void draw() {
        for (GridElement gridElement : this.firstLayer) {
            gridElement.draw();
        }
        for (GridElement gridElement : this.secondLayer) {
            gridElement.draw();
        }
    }
}
