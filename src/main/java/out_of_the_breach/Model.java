package out_of_the_breach;

import out_of_the_breach.levels.Level;
import out_of_the_breach.model.TerrainTile;

import java.util.List;

public class Model {
    private List<Level> levels;
    private int selectedLevel;
    private boolean inGame;

    public Model(List<Level> levels) {
        this.levels = levels;
        selectedLevel = 0;
        inGame = false;
    }

    public void nextLevel() {
        if (selectedLevel + 1 < levels.size()) {
            selectedLevel++;
        }
    }

    public void previousLevel() {
        if (selectedLevel > 0) {
            selectedLevel--;
        }
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public Level getSelectedLevel() {
        return levels.get(selectedLevel);
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getSelectedLevelNumber() {
        return selectedLevel;
    }
}
