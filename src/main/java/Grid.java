import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<GridElement> firstLayer;
    private List<GridElement> secondLayer;

    public Grid() {
        this.firstLayer = new ArrayList<>();
        this.secondLayer = new ArrayList<>();
    }
    public Grid(List<GridElement> firstLayer, List<GridElement> secondLayer) {
        this.firstLayer = firstLayer;
        this.secondLayer = secondLayer;
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
