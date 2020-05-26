package out_of_the_breach;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.CenteredComponentPosition;
import out_of_the_breach.GUI.GUIparentNode;

public class View extends GUIparentNode {
    private Model model;
    private GameView gameView;
    private BoardTilesComponent board;

    public View(Model model) {
        super(null, null, true);
        this.model = model;
        gameView = new GameView(model.getSelectedLevel());
        gameView.setEnabled(false);
        board = new BoardTilesComponent(model.getSelectedLevel(), new CenteredComponentPosition());

        addComponent(gameView);
        addComponent(board);
    }

    // We don't want to "sandbox" this component
    // That's why we override bondedDraw
    public void bondedDraw(TextGraphics buffer) {
        if (isEnabled()) {
            draw(buffer);
        }
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {
        if (model.isInGame()) {
            if (!gameView.processKeystroke(stroke)) {
                System.out.println("Hey!");
                model.setInGame(false);
            }
        } else {
            switch (stroke.getKeyType()) {
                case Enter:
                    model.setInGame(true);
                    gameView.setEnabled(true);
                    board.setEnabled(false);
                    break;
                case ArrowRight:
                    model.nextLevel();
                    gameView.setGameModel(model.getSelectedLevel());
                    board.setGameModel(model.getSelectedLevel());
                    break;
                case ArrowLeft:
                    model.previousLevel();
                    gameView.setGameModel(model.getSelectedLevel());
                    board.setGameModel(model.getSelectedLevel());
                    break;
            }
        }

        return true;
    }
}
