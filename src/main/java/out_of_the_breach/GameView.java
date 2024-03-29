package out_of_the_breach;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import out_of_the_breach.GUI.GUIcomponent;
import out_of_the_breach.GUI.GUIparentNode;
import out_of_the_breach.GUI.componentSelectionStrategy.RootSelectionStrategy;
import out_of_the_breach.model.GameModel;
import out_of_the_breach.model.GameStatus;

public class GameView extends GUIparentNode {
    private EnemyRoutedComponent enemyRoutedComponent;
    private GameModel gameModel;
    private BoardManager manager;
    private PowerGridComponent power;
    private EndTurnButton endButton;
    private GameOverDisplay gameOver;

    public GameView(GameModel gameModel) {
        super(null, null, true, new RootSelectionStrategy());

        this.gameModel = gameModel;

        TooltipComponent tooltip = new TooltipComponent();
        TerrainDescriptionComponent terrainDescription = new TerrainDescriptionComponent();
        EntityInfoComponent eic = new EntityInfoComponent();

        manager = new BoardManager(
                new BoardTilesComponent(gameModel),
                gameModel,
                new BoardGUIOverlay(gameModel, tooltip, terrainDescription, eic),
                tooltip
        );

        power = new PowerGridComponent(gameModel);

        endButton = new EndTurnButton(gameModel, tooltip);

        gameOver = new GameOverDisplay();
        gameOver.setEnabled(false);
        gameOver.setText("GAME OVER");

        enemyRoutedComponent = new EnemyRoutedComponent();

        addComponent(
                manager
        );

        addComponent(
                power
        );

        addComponent(
                endButton
        );

        addComponent(
                enemyRoutedComponent
        );

        addComponent(
                tooltip
        );

        addComponent(
                terrainDescription
        );

        addComponent(
                eic
        );

        addComponent(
                gameOver
        );
    }

    // We don't want to "sandbox" this component
    // That's why we override bondedDraw
    public void bondedDraw(TextGraphics buffer) {
        enemyRoutedComponent.setnTurns(gameModel.getTurns());
        if (gameModel.getGameStatus() == GameStatus.PLAYER_LOSES) {
            gameOver.setEnabled(true);
            gameOver.setText("GAME OVER");
        } else if (gameModel.getGameStatus() == GameStatus.PLAYER_WINS) {
            gameOver.setEnabled(true);
            gameOver.setText(" VICTORY!");
        } else {
            gameOver.setEnabled(false);
        }

        if (isEnabled()) {
            draw(buffer);
        }
    }

    public void setGameModel(GameModel gameModel) {
        gameOver.setEnabled(gameModel.getGameStatus() != GameStatus.GAME_IN_PROGRESS);
        manager.setGameModel(gameModel);
        power.setGameModel(gameModel);
        endButton.setGameModel(gameModel);
        this.gameModel = gameModel;
    }

    @Override
    public boolean processKeystroke(KeyStroke stroke) {
        if (gameModel.getGameStatus() != GameStatus.GAME_IN_PROGRESS) {
            return false;
        } else {
            return super.processKeystroke(stroke);
        }
    }
}
