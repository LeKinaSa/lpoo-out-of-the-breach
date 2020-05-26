package out_of_the_breach;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.GUIparentNode;

public class View extends GUIparentNode {
    private Model model;
    private GameView gameView;

    public View(Model model) {
        super(null, null, true);
        this.model = model;
        gameView = new GameView(model.getSelectedLevel());

        addComponent(gameView);
    }

    // We don't want to "sandbox" this component
    // That's why we override bondedDraw
    public void bondedDraw(TextGraphics buffer) {
        if (isEnabled()) {
            draw(buffer);
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) { // This function is straight up lifted from GUIRoot
        return gameView.processKeystroke(stroke);
    }
}
