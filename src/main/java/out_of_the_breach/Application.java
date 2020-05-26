package out_of_the_breach;

import out_of_the_breach.GUI.*;
import com.googlecode.lanterna.TextColor;
import out_of_the_breach.model.*;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, OutsideOfTheGrid {
        LanternaTerminal t  = new LanternaTerminal(110, 40);
        GUIRoot root        = new GUIRoot(t, new TextColor.RGB(40, 40, 40));
        GameModel gameModel = new Level1();
        GameView gameView   = new GameView(gameModel);

        // You can change the level with this function call
        gameView.setGameModel(gameModel);

        root.addComponent(
                gameView
        );

        while (true) {
            Thread.sleep(10);

            if (!root.processInput()) {
                break;
            }
            root.draw();
        }
        
    }
}
