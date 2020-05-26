package out_of_the_breach;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import out_of_the_breach.GUI.GUIcomponent;
import out_of_the_breach.GUI.GUIparentNode;
import out_of_the_breach.model.Model;

public class GameView extends GUIparentNode {
    private Model model;
    private BoardManager manager;
    private PowerGridComponent power;
    private EndTurnButton endButton;

    public GameView(Model model) {
        super(null, null, true);

        this.model = model;

        TooltipComponent tooltip = new TooltipComponent();
        TerrainDescriptionComponent terrainDescription = new TerrainDescriptionComponent();
        EntityInfoComponent eic = new EntityInfoComponent();

        manager = new BoardManager(
                new BoardTilesComponent(model),
                model,
                new BoardGUIOverlay(model, tooltip, terrainDescription, eic),
                tooltip
        );

        power = new PowerGridComponent(model);

        endButton = new EndTurnButton(model, tooltip);

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

    public void setModel(Model model) {
        this.model = model;
    }
}
