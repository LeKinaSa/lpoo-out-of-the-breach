package out_of_the_breach;

import out_of_the_breach.GUI.*;
import com.googlecode.lanterna.TextColor;
import out_of_the_breach.levels.Level;
import out_of_the_breach.levels.Level1;
import out_of_the_breach.levels.Level2;
import out_of_the_breach.levels.Level3;
import out_of_the_breach.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, OutsideOfTheGrid {
        LanternaTerminal t  = new LanternaTerminal(110, 40);
        GUIRoot root        = new GUIRoot(t, new TextColor.RGB(40, 40, 40));

        List<Level> levelList = new ArrayList<>();
        levelList.add(new Level1());
        levelList.add(new Level2());
        levelList.add(new Level3());
        View view = new View(new Model(levelList));

        root.addComponent(
                view
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
