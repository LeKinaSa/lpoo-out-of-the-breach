package out_of_the_breach;

import out_of_the_breach.GUI.*;
import com.googlecode.lanterna.TextColor;
import out_of_the_breach.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, OutsideOfTheGrid {
        LanternaTerminal t = new LanternaTerminal(110, 40);
        GUIRoot root       = new GUIRoot(t, new TextColor.RGB(40, 40, 40));
        Model model        = new Level1();
        root.addComponent(
                new GameView(model)
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
