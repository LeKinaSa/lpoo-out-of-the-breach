package out_of_the_breach;

import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.GUIparentNode;
import out_of_the_breach.model.GameModel;

public class GameView extends GUIparentNode {
    private GameModel gameModel;
    private BoardManager manager;
    private PowerGridComponent power;
    private EndTurnButton endButton;

    public GameView(GameModel gameModel) {
        super(null, null, true);

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
                new EnemyRoutedComponent()
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
    }

    // We don't want to "sandbox" this component
    // That's why we override bondedDraw
    public void bondedDraw(TextGraphics buffer) {
        if (isEnabled()) {
            draw(buffer);
        }
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
